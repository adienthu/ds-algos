package com.example;

public class Main {
    private static final int INF = Integer.MAX_VALUE/2 - 1;
    /**
     * Compute the shortest-path matrix given the weighted adjacency matrix using
     * <a href="http://www.cs.cornell.edu/~wdtseng/icpc/notes/graph_part3.pdf">Floyd-Warshall's Algorithm</a>.
     *
     * @param A weighted adjacency matrix
     * @param n number of nodes in the graph
     * @return shortest-path matrix
     */
    private static int[][] computeShortestPathMatrix(int[][] A, int n) {
        final int[][] p = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0;j < n;j++) {
                p[i][j] = (A[i][j] > 0) ? A[i][j] : INF;
            }
        }

        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                for (int k = 0;k < n;k++) {
                    p[j][k] = Math.min(p[j][k], p[j][i] + p[i][k]);
                }
            }
        }

        return p;
    }

    /**
     * Compute the shortest-path matrix given the weighted adjacency matrix using
     * <a href="http://www.cs.cornell.edu/~wdtseng/icpc/notes/graph_part3.pdf">Floyd-Warshall's Algorithm</a>.
     * This fills up the @param parent array for path recovery.
     *
     * @param A weighted adjacency matrix
     * @param n number of nodes in the graph
     * @param parent This matrix will be populated with the parent nodes for each pair.
     * @return shortest-path matrix
     */
    private static int[][] computeShortestPathMatrix2(int[][] A, int n, int[][] parent) {
        final int[][] p = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0;j < n;j++) {
                p[i][j] = (A[i][j] > 0) ? A[i][j] : INF;
            }
        }

        // Initialize parent matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0;j < n;j++) {
                if (i == j || p[i][j]==INF)
                    parent[i][j] = -1;
                else
                    parent[i][j] = i;
            }
        }

        for (int i = 0;i < n;i++) {
            for (int j = 0;j < n;j++) {
                for (int k = 0;k < n;k++) {
                    int d = p[j][i] + p[i][k];
                    if (d < p[j][k]) {
                        p[j][k] = d;
                        parent[j][k] = parent[i][k];
                    }
                }
            }
        }

        return p;
    }

    private static String recoverPath(int[][] p, int n, int src, int dest) {
        String path = Integer.toString(src);
        for (int i = 0;i < n;i++) {
            if (i != src && i != dest)
                if (p[src][i]+p[i][dest] == p[src][dest]) {
                    path += recoverPath(p, n, i, dest);
                    break;
                }
        }
        return path;
    }

    private static String recoverPath2(int[][] p, int n, int src, int dest) {
        int parent = p[src][dest];
        String path = "" + parent + dest;
        while (parent != src) {
            parent = p[src][parent];
            path = parent + path;
        }
        return path;
    }

    public static void main(String[] args) {
        final int n = 4;
        final int[][] W = new int[][] {
            {0, 2, 0, 7},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 0}
        };

        final int[][] parent = new int[n][n];

        System.out.println("Weight matrix:");
        printMatrix(W, n);

        System.out.println("\n\nShortest Path matrix:");
        final int[][] shortestPathMatrix = computeShortestPathMatrix(W, n);
        printMatrix(shortestPathMatrix, n);

        final int src = 0, dest = 3;
        final String path = recoverPath(shortestPathMatrix, n, src, dest) + Integer.toString(dest);
        System.out.println("Shortest path between nodes "+ src + " and " + dest + ": " + path);


        System.out.println("\n\nMethod 2: Shortest Path matrix:");
        final int[][] shortestPathMatrix2 = computeShortestPathMatrix2(W, n, parent);
        printMatrix(shortestPathMatrix2, n);

        final String path2 = recoverPath2(parent, n, src, dest);
        System.out.println("Method 2: Shortest path between nodes "+ src + " and " + dest + ": " + path2);
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
