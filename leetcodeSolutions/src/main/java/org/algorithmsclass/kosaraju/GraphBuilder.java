package org.algorithmsclass.kosaraju;

import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/*
 Builds the graph and the reverse graph from data in the file
 */
public class GraphBuilder {
    private static Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\SCC.txt");
    public static class Graphs {
        private DirectedGraph forwardGraph;

        public Graphs() {
            this.forwardGraph = new DirectedGraph();
        }
        public DirectedGraph getForwardGraph() {
            return this.forwardGraph;
        }
    }

    public static Graphs buildGraph(Integer size) throws IOException {
        Graphs graphs = new Graphs();
        Map<Integer, DirectedGraph.Value> adjacencyList = Maps.newHashMap();
        for(int i=1; i<=size; i++) {
            DirectedGraph.Value value = new DirectedGraph.Value();
            value.setAdjacencyList(Lists.newArrayList());
            adjacencyList.put(i, value);
        }
        graphs.forwardGraph.graph = adjacencyList;

        Files.lines(graphFilePath).forEach(word -> {
            StringTokenizer tokenizer = new StringTokenizer(word, " ");
            while(tokenizer.hasMoreElements()) {
                Integer fromVertex = Integer.parseInt(tokenizer.nextToken());
                Integer toVertex = Integer.parseInt(tokenizer.nextToken());
                graphs.getForwardGraph().addEdge(fromVertex, toVertex);
                graphs.getForwardGraph().isExplored.put(fromVertex, false);
                graphs.getForwardGraph().isExplored.put(toVertex, false);
            }

        });
        return graphs;
    }

    public static DirectedGraph reverseGraph(DirectedGraph forwardGraph, int size) {

        DirectedGraph reverseGraph = new DirectedGraph();
        Map<Integer, DirectedGraph.Value> adjacencyList = Maps.newHashMap();
        for(int i=1; i<=size; i++) {
            DirectedGraph.Value value = new DirectedGraph.Value();
            value.setAdjacencyList(Lists.newArrayList());
            adjacencyList.put(i, value);
        }
        reverseGraph.graph = adjacencyList;

        Iterator it = forwardGraph.getAdjacencyList().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry graphEntry = (Map.Entry)it.next();
            Integer toEntry = (Integer)graphEntry.getKey();
            DirectedGraph.Value value = (DirectedGraph.Value) graphEntry.getValue();
            List<Integer> fromEntriesList = value.getAdjacencyList();
            fromEntriesList.forEach((entry) -> {
                reverseGraph.addEdge(entry, toEntry);
                reverseGraph.isExplored.put(entry, false);
                reverseGraph.isExplored.put(toEntry, false);
            });
        }
        return reverseGraph;
    }
}
