package com.example;

public class Main {
    /**
     * Compute the path matrix given the adjacency matrix using 
     * <a href="http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Tremblay/L17-Warshall.htm">Warshall's Algorithm</a>.
     *
     * @param A adjacency matrix
     * @param n number of nodes in the graph
     * @return path matrix
     */
    private static int[][] computePathMatrix(int[][] A, int n) {
        final int[][] p = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, p[i], 0, n);
        }

        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                for (int k = 0;k < n;k++) {
                    if(p[j][k] == 1)
                        continue;
                    p[j][k] = p[j][i] * p[i][k];
                }
            }
        }

        return p;
    }


    public static void main(String[] args) {
        final int n = 3; // Nodes: 1, 2 and 3
        final int[][] A = new int[n][n];
        // no loops
        A[0][0] = 0;
        A[1][1] = 0;
        A[2][2] = 0;

        // 1 --> 2
        A[0][1] = 1;
        // 2 --> 3
        A[1][2] = 1;
        // 3 --> 1
        A[2][0] = 1;

        System.out.println("Adjacency matrix:");
        printMatrix(A, n);

        System.out.println("\n\nPath matrix:");
        final int[][] pathMatrix = computePathMatrix(A, n);
        printMatrix(pathMatrix, n);
    }

    private static void printMatrix(int[][] matrix, int n) {
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }
}
