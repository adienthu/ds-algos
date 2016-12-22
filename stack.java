package com.example;

import java.util.Arrays;

public class Main {

    static class Stack<T> {
        private Object[] array;
        private static final int INITIAL_CAPACITY = 5;
        private int top;

        public Stack() {
            array = new Object[INITIAL_CAPACITY];
            top = -1;
        }

        public void push(T element) {
            if (top == array.length-1) {
                // grow the array
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[++top] = element;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (top == -1)
                return null;
            return (T)array[top--];
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        Stack<Integer> stack = new Stack<>();
        for (int i : ar) {
            stack.push(i);
        }

        Integer i;
        while ((i = stack.pop()) != null) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
