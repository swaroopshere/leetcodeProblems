package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class KruskalsAlgorithm {
    private int vertexCount;
    private List<Edge> edgeList;
    private List<Edge> mst;
    UnionFind unionFind;

    private static class Edge implements Comparable<Edge>{
        private Integer firstVertex;
        private Integer secondVertex;
        private Integer cost;
        public Edge(Integer firstVertex, Integer secondVertex, Integer cost) {
            this.firstVertex = firstVertex;
            this.secondVertex = secondVertex;
            this.cost = cost;
        }
        public Integer getFirstVertex() {return this.firstVertex;}
        public Integer getSecondVertex() {return this.secondVertex;}
        public int getCost() {return this.cost;}

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.getCost(), edge.getCost());
        }
    }

    public KruskalsAlgorithm() throws IOException {
        this.mst = Lists.newArrayList();
        this.edgeList = buildEdgeList();
        this.unionFind = new UnionFind(vertexCount);
    }

    public void calculate() {
        //sort edges
        Integer mstCost = 0;
        Collections.sort(edgeList);
        Iterator<Edge> it = edgeList.iterator();
        Edge maxSpacingEdge = null;
        while(it.hasNext()) {
            Edge edge = it.next();
            unionFind.union(edge.getFirstVertex(), edge.getSecondVertex());
            mstCost+=edge.getCost();
            if(unionFind.getComponentsSize() < 4) {
                maxSpacingEdge = edge;
                break;
            }
        }
        System.out.println("mstCost = " + mstCost);
        System.out.println("max spacing = " + maxSpacingEdge.getCost());
    }

    private List<Edge> buildEdgeList() throws IOException {
        List<Edge> adjList = Lists.newArrayList();
        Path graphFilePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\clustering1.txt");
        Files.lines(graphFilePath).forEach(word -> {
            StringTokenizer tokenizer = new StringTokenizer(word, " ");
            while(tokenizer.hasMoreElements()) {
                if (tokenizer.countTokens() == 1) {
                    vertexCount = Integer.parseInt(tokenizer.nextToken());
                } else {
                    int fromVertex = Integer.parseInt(tokenizer.nextToken());
                    int toVertex = Integer.parseInt(tokenizer.nextToken());
                    int cost = Integer.parseInt(tokenizer.nextToken());
                    adjList.add(new Edge(fromVertex, toVertex, cost));
                }
            }
        });
        return adjList;
    }
}
