package com.example;

public class Main {

    private static void quicksort(int[] ar, int low, int high) {
        if (low >= high)
            return;

        int pivot = ar[high];
        for (int i = low; i < high; i++) {
            if (ar[i] <= pivot) {
                int temp = ar[low];
                ar[low] = ar[i];
                ar[i] = temp;
                low++;
            }
        }

        // Put pivot in place
        int temp = ar[high];
        ar[high] = ar[low];
        ar[low] = temp;

        quicksort(ar, 0, low-1);
        quicksort(ar, low+1, high);
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
//        int[] ar = {18, 6};
        quicksort(ar, 0, ar.length-1);
        printArray(ar);
    }

    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }
}
