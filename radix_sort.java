package com.example;

import java.io.IOException;

public class Main {

    private static int leastSignificantDigit(int num, int d) {
        final int divisor = (int)Math.pow(10, d);
        int r = num % divisor;
        return (r / (divisor/10));
    }

    private static int[] decimalCountingSort(int[] A, int d) {
        final int[] B = new int[A.length];
        final int[] D = new int[A.length];
        final int[] C = new int[10];

        for (int j = 0;j < A.length;j++) {
            D[j] = leastSignificantDigit(A[j], d);
        }

        for (int j = 0;j < A.length;j++) {
            C[D[j]]++;
        }

        for (int i = 1; i < C.length;i++) {
            C[i] = C[i] + C[i-1];
        }

        for (int j = A.length-1;j >= 0;j--) {
            B[C[D[j]] - 1] = A[j];
            C[D[j]]--;
        }

        return B;
    }

    private static int[] radixSort(int[] A, int digits) {
        for (int i = 1; i <= digits;i++) {
            A = decimalCountingSort(A, i);
        }
        return A;
    }

    public static void main(String[] args) throws IOException {
        int[] ar = {329, 457, 657, 839, 436, 720, 355};//{1, 4, 1, 2, 7, 5, 2};//{2, 5, 3, 0, 2, 3, 0, 3};
        int[] result = radixSort(ar, 3);
        printArray(result);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
