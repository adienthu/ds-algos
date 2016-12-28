package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class Main {

    static class Vertex {
        private final Integer val;
        private List<Edge> edges;

        public Vertex(Integer val) {
            this.val = val;
        }

        public void addEdge(Vertex v2) {
            if (edges == null)
                edges = new LinkedList<>();
            for (Edge e : edges) {
                if (e.target.equals(v2)) { // edge already exists so do nothing
                    return;
                }
            }

            // new edge
            edges.add(new Edge(v2));
        }

        public void removeEdge(Vertex v2) {
            for (Edge e : edges) {
                if (e.target.equals(v2)) {
                    edges.remove(e);
                    return;
                }
            }
        }

        public List<Vertex> getNeighbours() {
            List<Vertex> list = new ArrayList<>();
            for (Edge e : edges) {
                list.add(e.target);
            }
            return list;
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

        public void removeEdge(Integer src, Integer target) {
            Vertex v1 = vertices.get(src);
            Vertex v2 = vertices.get(target);
            if (v1 == null || v2 == null)
                return;

            v1.removeEdge(v2);
        }

        public void printBreadthFirst(Integer startVal) {
            Vertex start = vertices.get(startVal);
            if (start == null)
                return;

            List<Vertex> visited = new ArrayList<>();
            visited.add(start);
            Queue<Vertex> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                Vertex v = queue.remove();
                System.out.print(v.val + " ");
                for (Edge e : v.getEdges()) {
                    Vertex neighbour = e.getTarget();
                    if (!visited.contains(neighbour)) {
                        queue.add(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }

        public void printDepthFirst(Integer startVal) {
            Vertex start = vertices.get(startVal);
            if (start == null)
                return;

            List<Vertex> visited = new ArrayList<>();
            visited.add(start);
            Stack<Vertex> stack = new Stack<>();
            stack.add(start);

            while (!stack.empty()) {
                Vertex v = stack.pop();
                System.out.print(v.val + " ");
                for (Edge e : v.getEdges()) {
                    Vertex neighbour = e.getTarget();
                    if (!visited.contains(neighbour)) {
                        stack.push(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
//        g.addEdge(1, 2);
//        g.addEdge(1, 3);
//        g.addEdge(2, 3);
//        g.addEdge(3, 4);
//        g.addEdge(4, 1);
//        g.printBreadthFirst(1);

        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.printBreadthFirst(2);
        System.out.println();
        g.printDepthFirst(2);
    }
}
