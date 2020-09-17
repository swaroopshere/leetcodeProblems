package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;
import com.google.inject.internal.util.Sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PrimsAlgorithm {
    private Map<Integer, List<Edge>> adjacencyList;
    private int vertexCount;
    private int edgeCount;
    private static class Edge {
        private int fromVertex;
        private int toVertex;
        private int cost;
        public Edge(int fromVertex, int toVertex, int cost) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.cost = cost;
        }
        public int getFromVertex() {return this.fromVertex;}
        public int getToVertex() {return this.toVertex;}
        public int getCost() {return this.cost;}
    }
    PriorityQueue<Edge> heap;

    public PrimsAlgorithm() throws IOException {
        this.adjacencyList = buildAdjacencyList();
        this.heap = new PriorityQueue<>(vertexCount, new EdgeCostComparator());
    }

    public void calculate() {
        //sort edges
    }

    public static class EdgeCostComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge n1, Edge n2) {
            return Integer.compare(n1.getCost(), n2.getCost());
        }
    }

    public void buildMST() {
        long cost = 0;
        int currentKey = 1;
        //set of explored vertices
        Map<Integer, Boolean> exploredVertices = Maps.newHashMap();
        //set of unexplored vertices
        Set<Integer> unexploredVertices = Sets.newHashSet();
        unexploredVertices.addAll(adjacencyList.keySet());
        exploredVertices.put(1, true);
        unexploredVertices.add(1);
        while(exploredVertices.size() != adjacencyList.size()) {
            List<Edge> currentNodeOutgoingEdges = adjacencyList.get(currentKey);
            //push these into Heap
            currentNodeOutgoingEdges.forEach(edge -> {
                if(exploredVertices.get(edge.getToVertex()) == null) {
                    heap.add(edge);
                }
            });
            Edge minCostNode = heap.poll();
            if(exploredVertices.get(minCostNode.getToVertex()) != null) {
                //remove
                unexploredVertices.remove(minCostNode.getToVertex());
            } else {
                //add to explored node, remove from (V-X)
                int toVertex = minCostNode.getToVertex();
                exploredVertices.put(toVertex, true);
                unexploredVertices.remove(toVertex);
                //remove node from the heap
                heap.remove(minCostNode);
                currentKey = toVertex;
                //add to cost
                cost+= minCostNode.getCost();
            }
        }
        System.out.println("MST cost = " + cost );
    }

    private Map<Integer, List<Edge>> buildAdjacencyList() throws IOException {
        Map<Integer, List<Edge>> adjList = Maps.newHashMap();
        for(int i=1; i<=500; i++) {
            List<Edge> edgeList = Lists.newArrayList();
            adjList.put(i, edgeList);
        }
        Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\edges.txt");
        Files.lines(graphFilePath).forEach(word -> {
            StringTokenizer tokenizer = new StringTokenizer(word, " ");
            while(tokenizer.hasMoreElements()) {
                if (tokenizer.countTokens() == 2) {
                    vertexCount = Integer.parseInt(tokenizer.nextToken());
                    edgeCount = Integer.parseInt(tokenizer.nextToken());
                } else {
                    int fromVertex = Integer.parseInt(tokenizer.nextToken());
                    int toVertex = Integer.parseInt(tokenizer.nextToken());
                    int cost = Integer.parseInt(tokenizer.nextToken());
                    adjList.get(fromVertex).add(new Edge(fromVertex, toVertex, cost));
                    adjList.get(toVertex).add(new Edge(toVertex, fromVertex, cost));
                }
            }
        });
        return adjList;
    }
}
