package com.example;

public class Main {

    static class AVLNode {
        AVLNode left, right, parent;
        private int data, height;

        public AVLNode(int data) {
            this.data = data;
        }

        public static int getHeight(AVLNode node) {
            return (node != null) ? node.height : -1;
        }

        public void updateHeight() {
            height = Math.max(getHeight(left), getHeight(right)) + 1;
        }

        public void insert(AVLNode node) {
            if (node == null)
                return;

            if (node.data <= this.data) {
                if (left == null) {
                    left = node;
                    left.parent = this;
                }else
                    left.insert(node);
            }else {
                if (right == null) {
                    right = node;
                    right.parent = this;
                }else
                    right.insert(node);
            }

            updateHeight();
        }

        public void printInOrder() {
            if (left != null)
                left.printInOrder();
            System.out.print(data + " ");
            if (right != null)
                right.printInOrder();
        }
    }

    static class AVLTree {
        private AVLNode root;

        public AVLTree(AVLNode root) {
            this.root = root;
        }

        public boolean isBalanced() {
            if (root == null)
                return true;
            final int leftSubtreeHeight = AVLNode.getHeight(root.left) + 1;
            final int rightSubtreeHeight = AVLNode.getHeight(root.right) + 1;
            return Math.abs(leftSubtreeHeight - rightSubtreeHeight) <= 1;
        }

        private void rotateRight(AVLNode u) {
            AVLNode l = u.left;

            u.left = l.right;
            if (u.left != null)
                u.left.parent = u;

            l.parent = u.parent;
            if (u.parent == null)
                root = l;
            else {
                if (u == u.parent.left)
                    u.parent.left = l;
                else
                    u.parent.right = l;
            }

            l.right = u;
            u.parent = l;

            u.updateHeight();
            l.updateHeight();
        }

        private void rotateLeft(AVLNode u) {
            AVLNode r = u.right;

            u.right = r.left;
            if (u.right != null)
                u.right.parent = u;

            r.parent = u.parent;
            if (u.parent == null)
                root = r;
            else {
                if (u == u.parent.left)
                    u.parent.left = r;
                else
                    u.parent.right = r;
            }

            r.left = u;
            u.parent = r;

            u.updateHeight();
            r.updateHeight();
        }

        public void insert(AVLNode node) {
            if (root == null) {
                root = node;
            }else {
                // 1. Insert the node.
                // 2. Check for the nearest imbalanced ancestor and determine insertion type - LL, RR, LR or RL.
                // 3. Perform rotation(s) to balance the tree.

                root.insert(node);

                // Find nearest unbalanced ancestor
                AVLNode ancestor = node.parent.parent;

                while (ancestor != null) {
                    int leftSubtreeHeight = AVLNode.getHeight(ancestor.left) + 1;
                    int rightSubtreeHeight = AVLNode.getHeight(ancestor.right) + 1;
                    if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) == 2) {
                        // unbalanced ancestor found
                        if (node.data <= ancestor.data) {
                            if (node.data <= ancestor.left.data) { // LL
                                rotateRight(ancestor);
                            }else { // LR
                                rotateLeft(ancestor.left);
                                rotateRight(ancestor);
                            }
                        }else {
                            if (node.data > ancestor.right.data) { // RR
                                rotateLeft(ancestor);
                            }else { // RL
                                rotateRight(ancestor.right);
                                rotateLeft(ancestor);
                            }
                        }

                        // update height of the remaining ancestors after rotation
                        AVLNode p = ancestor.parent;
                        while (p != null) {
                            p.updateHeight();
                            p = p.parent;
                        }

                        break;
                    }

                    ancestor = ancestor.parent;
                }
            }
        }

        public void printInOrder() {
            if (root != null)
                root.printInOrder();
        }
    }

    public static void main(String[] args) {
        int[] ar = {18, 6, 9, 1, 4, 15, 12, 5, 6, 7, 11, 20};
//        int[] ar = {4, 3, 2};
//        int[] ar = {64, 1, 14};
//        int[] ar = {64, 1, 14, 26, 13, 110, 98, 85};
        AVLNode root = new AVLNode(ar[0]);
        AVLTree tree = new AVLTree(root);
        for (int i = 1;i < ar.length; i++) {
            tree.insert(new AVLNode(ar[i]));
            if (!tree.isBalanced()) {
                System.out.println("Tree not balanced on inserting " + ar[i]);
                break;
            }
        }

        tree.printInOrder();
    }
}
