package com.example;

public class Main {

    private static void maxHeapify(Integer[] A, int i, int n) {
        int leftIndex = i << 1;
        int rightIndex = (i << 1) | 1;
        final int maxIndex = n-1;
        int largest = i;

        if (leftIndex <= maxIndex && A[leftIndex] > A[i])
            largest = leftIndex;
        if (rightIndex <= maxIndex && A[rightIndex] > A[largest])
            largest = rightIndex;

        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            maxHeapify(A, largest, n);
        }

    }

    private static void buildMaxHeap(Integer[] A) {
        // The elements floor(N/2+1)...N are leaves and satisfy the max-heap property
        // So we iterate over the remaining elements from floor(N/2)...1 and heapify
        final int startIndex = (int)Math.floor((A.length-1)/2);
        for(int i = startIndex; i >= 0; i--) {
            maxHeapify(A, i, A.length);
        }
    }

    private static void heapSort(Integer[] A) {
        buildMaxHeap(A);
        int heapSize = A.length;
        for (int i = A.length-1; i > 0; i--) {
            // Discard the root from the heap
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapSize--;
            // The root may now be violating the max-heap property so heapify
            maxHeapify(A, 0, heapSize);
        }
    }

    public static void main(String[] args) {
//        Integer[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        Integer[] ar = {23, 1, 45, 2, 33, 6, 2, 67};
        heapSort(ar);
        printArray(ar);
    }

    private static void printArray(Integer[] ar) {
        for(Integer n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
