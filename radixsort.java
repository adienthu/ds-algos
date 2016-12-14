package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static void radixSort(int[] ar, int maxDigits) {

        for (int i = 1; i <= maxDigits; i++) {
            // Create the buckets
            Map<Integer, List<Integer>> buckets = new HashMap<>(10);
            for (int num : ar) {
                String numStr = String.valueOf(num);
                if (numStr.length() >= i) {
                    // get the ith least significant digit
                    int digit = Integer.valueOf(numStr.substring(numStr.length() - i, numStr.length() - i + 1));
                    List<Integer> bucket = buckets.get(digit);
                    if (bucket == null) {
                        bucket = new ArrayList<>();
                        buckets.put(digit, bucket);
                    }
                    bucket.add(num);
                }else {
                    List<Integer> bucket = buckets.get(0);
                    if (bucket == null) {
                        bucket = new ArrayList<>();
                        buckets.put(0, bucket);
                    }
                    bucket.add(num);
                }
            }

            for (int j = 0, k = 0; j < 10; j++) {
                List<Integer> bucket = buckets.get(j);
                if (bucket != null) {
                    for (Integer num : bucket) {
                        ar[k++] = num;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
//        int[] ar = {18, 6};
        radixSort(ar, 2);
        printArray(ar);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
