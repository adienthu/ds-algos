package com.example;

import java.util.Arrays;

public class Main {

    private static void quicksort(int[] ar) {

        if (ar.length <= 1)
            return;

        // Get midpoint
        int mid = ar.length / 2;
        int midValue = ar[mid];
        int[] left = new int[ar.length];
        int[] right = new int[ar.length];
        int j = 0, k = 0;
        
        // Partition
        for (int i = 0;i < ar.length;i++) {
            if (i != mid) {
                if (ar[i] <= midValue)
                    left[j++] = ar[i];
                else
                    right[k++] = ar[i];
            }
        }

        left = Arrays.copyOf(left, j);
        right = Arrays.copyOf(right, k);
        
        // Sort the partitions
        quicksort(left);
        quicksort(right);
        
        // Merge 
        int i = 0;
        for (;i < j; i++) {
            ar[i] = left[i];
        }

        ar[i++] = midValue;

        for (int p = 0;p < k; p++, i++) {
            ar[i] = right[p];
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
//        int[] ar = {18, 6};
        quicksort(ar);
        printArray(ar);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
