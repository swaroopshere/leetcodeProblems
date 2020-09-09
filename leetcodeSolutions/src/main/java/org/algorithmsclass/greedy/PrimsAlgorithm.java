package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.StringTokenizer;

public class PrimsAlgorithm {
    private Map<Integer, Node> adjacencyList;
    private static class Node {
        private int toVertex;
        private int cost;
        public Node(int toVertex, int cost) {
            this.toVertex = toVertex;
            this.cost = cost;
        }
        public int getToVertex() {return toVertex;}
        public int getCost() {return cost;}
    }

    public void buildAdjacencyList() throws IOException {
        Map<Integer, Node> adjList = Maps.newHashMap();
        Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\edges.txt");
        Files.lines(graphFilePath).forEach(word -> {
            StringTokenizer tokenizer = new StringTokenizer(word, " ");
            while(tokenizer.hasMoreElements()) {
                if (tokenizer.countTokens() == 2) {
                    tokenizer.nextToken();
                    tokenizer.nextToken();
                } else {
                    int fromVertex = Integer.parseInt(tokenizer.nextToken());
                    int toVertex = Integer.parseInt(tokenizer.nextToken());
                    int cost = Integer.parseInt(tokenizer.nextToken());
                }
            }
        }
    }
}
