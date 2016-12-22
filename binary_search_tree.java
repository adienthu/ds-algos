package com.example;

public class Main {

    // Binary Search Tree Node
    static class Node {
        private Node left, right;
        private int data;

        public Node(int data) {
            this.data = data;
        }

        public void insert(int data) {
            if (data <= this.data) {
                if (left == null)
                    left = new Node(data);
                else
                    left.insert(data);
            }else {
                if (right == null)
                    right = new Node(data);
                else
                    right.insert(data);
            }
        }

        public void printInOrder() {
            if (left != null)
                left.printInOrder();
            System.out.print(data + " ");
            if (right != null)
                right.printInOrder();
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11};
        Node root = new Node(ar[0]);
        for (int i = 1;i < ar.length-1; i++) {
            root.insert(ar[i]);
        }

        root.printInOrder();
    }
}
