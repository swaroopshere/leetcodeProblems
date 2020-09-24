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

public class DikjstraAlgorithm {
    private Map<Integer, List<Edge>> adjacencyList;
    private Map<Integer, Integer> costMap;
    private static Integer MAX_DISTANCE = 1000000;
    private static Integer NUM_NODES = 200;
    private static class Edge implements Comparable<Edge>{
        private int fromVertex;
        private int toVertex;
        private int cost;
        private int intermediateCost;
        public Edge(int fromVertex, int toVertex, int cost) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.cost = cost;
            this.intermediateCost = 0;
        }
        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.intermediateCost, edge.intermediateCost);
        }
    }

    public DikjstraAlgorithm() throws IOException {
        this.adjacencyList = buildAdjacencyList();
        this.costMap = Maps.newHashMap();
        adjacencyList.keySet().forEach(key -> costMap.put(key, MAX_DISTANCE));
    }

    public void calculate() {
        Set<Integer> coveredVertices = Sets.newHashSet();
        Set<Integer> allVertices = Sets.newHashSet();
        Set<Integer> uncoveredVertices = Sets.newHashSet();
        for(int i =1; i<=NUM_NODES;i++){
            allVertices.add(i);
            uncoveredVertices.add(i);
        }
        coveredVertices.add(1);
        uncoveredVertices.remove(1);
        costMap.replace(1, 0);
        while(coveredVertices.size() < allVertices.size()) {
            Edge minCostEdge = getMinimumCostEdge(coveredVertices, uncoveredVertices);
            coveredVertices.add(minCostEdge.toVertex);
            uncoveredVertices.remove(minCostEdge.toVertex);
        }
        System.out.println(costMap.get(7) + "," + costMap.get(37) + "," + costMap.get(59) + "," + costMap.get(82) + "," + costMap.get(99) + "," + costMap.get(115) + "," + costMap.get(133) + "," + costMap.get(165) + "," + costMap.get(188) + "," + costMap.get(197));
    }

    private Edge getMinimumCostEdge(Set<Integer> coveredVertices, Set<Integer> uncoveredVertices) {
        //first, get all edges from X to V-X
        List<Edge> allOutgoingEdges = Lists.newArrayList();
        coveredVertices.forEach(fromVertex -> {
            List<Edge> edgesPerVertex = adjacencyList.get(fromVertex).stream().filter(edge -> uncoveredVertices.contains(edge.toVertex)).collect(Collectors.toList());
            //calculate intermediate costs for each
            edgesPerVertex.forEach(edge -> {
                int intermediateCost = costMap.get(edge.fromVertex) + edge.cost;
                //update costMap
                if(intermediateCost < costMap.get(edge.toVertex)) {
                    costMap.replace(edge.toVertex, intermediateCost);
                }
                edge.intermediateCost = costMap.get(edge.toVertex);
            });
            allOutgoingEdges.addAll(edgesPerVertex);
        });
        //get edge with least cost
        Collections.sort(allOutgoingEdges);
        return allOutgoingEdges.get(0);
    }

    public Map<Integer, List<Edge>> buildAdjacencyList() throws IOException {
        Map<Integer, List<Edge>> adjList = Maps.newHashMap();
        for(int i=1; i<=NUM_NODES; i++) {
            List<Edge> edgeList = Lists.newArrayList();
            adjList.put(i, edgeList);
        }
        Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\dijkstraData.txt");
        Files.lines(graphFilePath).forEach(word -> {
            StringTokenizer tokenizer = new StringTokenizer(word, "\t");
            int fromVertex = 0;
            while(tokenizer.hasMoreElements()) {
                String token = tokenizer.nextToken();
                int toVertex = 0;
                int cost = 0;
                if(token.contains(",")) {
                    String[] tokens = token.split(",");
                    toVertex = Integer.parseInt(tokens[0]);
                    cost = Integer.parseInt(tokens[1]);
                    adjList.get(fromVertex).add(new Edge(fromVertex, toVertex, cost));
                } else {
                    fromVertex = Integer.parseInt(token);
                }
            }
        });
        return adjList;
    }
}
