package com.example;

// Ref: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java
public class Main {

    private static void merge(int[] ar, int[] tmp, int left, int mid, int right) {
        int i = left, j = mid+1;
        int k = left;
        while (i <= mid && j <= right) {
            if (ar[i] <= ar[j])
                tmp[k] = ar[i++];
            else
                tmp[k] = ar[j++];
            k++;
        }

        while (i <= mid) {
            tmp[k++] = ar[i++];
        }

        while (j <= right) {
            tmp[k++] = ar[j++];
        }

        for (int p = left; p <= right; p++) {
            ar[p] = tmp[p];
        }
    }

    private static void mergeSort(int[] ar, int[] tmp, int left, int right) {
        if (left >= right)
            return;

        // Split the array into two halves
        int mid = (left + right) / 2;
        // Sort the first half
        mergeSort(ar, tmp, left, mid);
        // Sort the second half
        mergeSort(ar, tmp, mid+1, right);
        // Merge
        merge(ar, tmp, left, mid, right);
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        int[] tmp = new int[ar.length];
        mergeSort(ar, tmp, 0, ar.length-1);
        printArray(ar);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
