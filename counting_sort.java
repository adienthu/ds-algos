package com.example;

public class Main {

    private static int[] countingSort(int[] A, int k) {
        final int[] B = new int[A.length];
        final int[] C = new int[k+1];

        for (int j = 0;j < A.length;j++) {
            C[A[j]]++;
        }

        for (int i = 1; i < C.length;i++) {
            C[i] = C[i] + C[i-1];
        }

        for (int j = A.length-1;j >= 0;j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }

        return B;
    }

    private static int maxValue(int[] A) {
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max)
                max = A[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] ar = {1, 4, 1, 2, 7, 5, 2};//{2, 5, 3, 0, 2, 3, 0, 3};
        final int max = maxValue(ar);
        int[] result = countingSort(ar, max);
        printArray(result);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
