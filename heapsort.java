package com.example;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    private static Integer[] heapSort(Integer[] a) {
        Queue<Integer> heap = new PriorityQueue<>();
        heap.addAll(Arrays.asList(a));
        int i = 0;
        while (!heap.isEmpty()) {
            a[i++] = heap.remove();
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        Integer[] result = heapSort(ar);
        printArray(result);
    }

    private static void printArray(Integer[] ar) {
        for(Integer n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
