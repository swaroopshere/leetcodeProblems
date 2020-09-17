package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class KruskalsAlgorithm {
    private int vertexCount;
    private List<Edge> edgeList;
    private Map<Integer, Integer> leaderMap;
    private Map<Integer, ConnectedComponent> allComponents;
    private List<Edge> mst;

    public static class ConnectedComponent {
        private Integer leader;
        private List<Integer> componentList;
        public ConnectedComponent(Integer leader, List<Integer> componentList) {
            this.leader = leader;
            this.componentList = componentList;
        }
        public Integer getLeader() {return leader;}
        public List<Integer> getComponentList() {return componentList;}
        public void mergeConnectedComponent(ConnectedComponent fromComponent) {
            fromComponent.getComponentList().forEach(vertex -> {
                componentList.add(vertex);
            });
        }
    }

    private static class Edge implements Comparable<Edge>{
        private Integer firstVertex;
        private Integer secondVertex;
        private int cost;
        public Edge(Integer firstVertex, Integer secondVertex, int cost) {
            this.firstVertex = firstVertex;
            this.secondVertex = secondVertex;
            this.cost = cost;
        }
        public Integer getFirstVertex() {return this.firstVertex;}
        public Integer getSecondVertex() {return this.secondVertex;}
        public int getCost() {return this.cost;}

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.cost, edge.cost);
        }
    }

    public KruskalsAlgorithm() throws IOException {
        this.mst = Lists.newArrayList();
        this.allComponents = Maps.newHashMap();
        this.leaderMap = Maps.newHashMap();
        this.edgeList = buildEdgeList();
    }

    public void calculate() {
        //sort edges
        Integer mstCost = 0;
        Collections.sort(edgeList);
        Iterator<Edge> it = edgeList.iterator();
        while(it.hasNext()) {
            Edge edge = it.next();
            Integer firstVertexLeader = leaderMap.get(edge.getFirstVertex());
            Integer secondVertexLeader = leaderMap.get(edge.getSecondVertex());
            if(firstVertexLeader != secondVertexLeader) {
                buildUnion(allComponents.get(firstVertexLeader), allComponents.get(secondVertexLeader));
                mstCost+=edge.getCost();
            }

            if(allComponents.size() <= 4) {
                break;
            }
        }
        System.out.println("mstCost = " + mstCost);
        System.out.println("max spacing = " + it.next().getCost());


    }

    private void buildUnion(ConnectedComponent c1, ConnectedComponent c2) {
        ConnectedComponent bigComponent = (c1.getComponentList().size() > c2.getComponentList().size()) ? c1 : c2;
        ConnectedComponent smallComponent = (c1.getComponentList().size() <= c2.getComponentList().size()) ? c1 : c2;
        allComponents.remove(smallComponent.leader);
        Integer newLeader = bigComponent.getLeader();
        smallComponent.leader = newLeader;
        bigComponent.mergeConnectedComponent(smallComponent);
        smallComponent.getComponentList().forEach(vertex -> {
            leaderMap.replace(vertex, newLeader);
        });
        allComponents.replace(bigComponent.leader, bigComponent);
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
                    allComponents.computeIfAbsent(fromVertex, s ->  new ConnectedComponent(fromVertex, Lists.newArrayList(fromVertex)));
                    allComponents.computeIfAbsent(toVertex, s -> new ConnectedComponent(toVertex, Lists.newArrayList(toVertex)));
                    leaderMap.put(fromVertex, fromVertex);
                    leaderMap.put(toVertex, toVertex);
                }
            }
        });
        return adjList;
    }
}
