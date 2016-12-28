import java.io.*;
import java.util.*;

public class Solution {
    static class Vertex {
        private final Integer val;
        private List<Edge> edges;

        public Vertex(Integer val) {
            this.val = val;
            this.edges = new LinkedList<>();
        }

        public Integer getValue() {
            return val;
        }

        public void addEdge(Vertex v2) {
            for (Edge e : edges) {
                if (e.target.equals(v2)) { // edge already exists so do nothing
                    return;
                }
            }

            // new edge
            edges.add(new Edge(v2));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Vertex))
                return false;
            Vertex o = (Vertex)obj;
            return this.val.equals(o.val);
        }
    }

    static class Edge {
        private final Vertex target;

        public Edge(Vertex target) {
            this.target = target;
        }

        public Vertex getTarget() {
            return target;
        }
    }

    static class Graph {
        private TreeMap<Integer, Vertex> vertices;

        public Graph() {
            vertices = new TreeMap<>();
        }

        public Set<Integer> getVertices() {
            return vertices.keySet();
        }

        public Vertex addVertex(Integer val) {
            Vertex v = vertices.get(val);
            if (v == null) {
                v = new Vertex(val);
                vertices.put(val, v);
            }
            return v;
        }

        public void addEdge(Integer src, Integer target) {
            Vertex v1 = vertices.get(src);
            if (v1 == null)
                v1 = addVertex(src);

            Vertex v2 = vertices.get(target);
            if (v2 == null)
                v2 = addVertex(target);

            v1.addEdge(v2);
        }

        public List<Integer> getAllReachableVertices(Integer startVal) {
            Vertex start = vertices.get(startVal);
            if (start == null)
                return Collections.emptyList();

            List<Integer> visited = new ArrayList<>();
            visited.add(start.getValue());
            Queue<Vertex> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                Vertex v = queue.remove();
                for (Edge e : v.getEdges()) {
                    Vertex neighbour = e.getTarget();
                    if (!visited.contains(neighbour.getValue())) {
                        visited.add(neighbour.getValue());
                        queue.add(neighbour);
                    }
                }
            }

            return visited;
        }
    }


        public static void main(String[] args) throws Exception{

//        long start = System.nanoTime();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int I = Integer.parseInt(temp[1]);

        Graph g = new Graph();

        for(int i = 0; i < I; i++){
            temp = bfr.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            // Store a and b in an appropriate data structure of your choice
            g.addEdge(a, b);
            g.addEdge(b, a);
        }

        Set<Integer> allVertices = g.getVertices();
        // Add solitary vertices
        for (int i = 0;i < N;i++) {
            if (!allVertices.contains(i)) {
                g.addVertex(i);
            }
        }

        allVertices = g.getVertices();
//        System.out.println("Vertices: " + allVertices);

        List<Integer> subgraphSizes = new ArrayList<>();
        HashMap<Integer, Integer> sizeMap = new HashMap<>();
        while (!allVertices.isEmpty()) {
            Integer v = allVertices.iterator().next();
            List<Integer> reachableVertices = g.getAllReachableVertices(v);
            final int size = reachableVertices.size();
            subgraphSizes.add(size);
            allVertices.removeAll(reachableVertices);

            // update map
            Integer count = sizeMap.get(size);
            if (count == null) count = 0;
            sizeMap.put(size, count+1);
        }

        //System.out.println("Subgraphs: " + subgraphSizes);
        //System.out.println("Size map: " + sizeMap);

        long combinations = 0;

        // Compute the final answer - the number of combinations
        for (int i = 0;i < subgraphSizes.size();i++) {
            final int size = subgraphSizes.get(i);
            for (Integer S : sizeMap.keySet()) {
                // find total number of astronauts from countries of size S
                int countS = sizeMap.get(S) * S;
                // if current country size is in S bucket then subtract
                if (S == size)
                    countS -= size;
                int numCombinations = size * countS;
                combinations += numCombinations;
            }

            // update map
            int count = sizeMap.get(size);
            sizeMap.put(size, count-1);
        }

        System.out.println(combinations);

        //long end = System.nanoTime();
        //System.out.println((end - start) / Math.pow(10, 9) + " seconds");

    }
 
}
