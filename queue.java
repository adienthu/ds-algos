package com.example;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    static class Queue<T> {
        private Object[] array;
        private static final int INITIAL_CAPACITY = 5;
        private int front, end;

        public Queue() {
            array = new Object[INITIAL_CAPACITY];
            front = end = -1;
        }

        public void enqueue(T element) {
            if (end == array.length-1) {
                // grow the array
                System.out.println("Growing the queue...");
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[++end] = element;
            // if its the first element then increment front
            if (end == 0) {
                front = 0;
            }
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (end == -1)
                return null;

            T element = (T)array[front];

            front++;
            if (front > end)
                front = end = -1;

            return element;
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        Queue<Integer> queue = new Queue<>();
        for (int i : ar) {
            queue.enqueue(i);
        }

        Integer i;
        while ((i = queue.dequeue()) != null) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
