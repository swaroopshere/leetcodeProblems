package org.algorithmsclass.kosaraju;

import com.google.inject.internal.util.Maps;

import java.io.IOException;
import java.util.*;

public class KosarajuAlgorithm {
    public Integer t = 0;
    public Integer s = null;
    public DirectedGraph reverseGraph;
    public DirectedGraph forwardGraph;
    Map<Integer, Integer> finishingTimeLabelMap = Maps.newHashMap();
    public static Integer NUMBER_OF_VERTICES = 875714;

    public KosarajuAlgorithm() throws IOException {
        GraphBuilder.Graphs graphs = GraphBuilder.buildGraph(NUMBER_OF_VERTICES);
        this.forwardGraph = graphs.getForwardGraph();
        this.reverseGraph = GraphBuilder.reverseGraph(forwardGraph, NUMBER_OF_VERTICES);
    }

    public void runDfsLoop() {
        for (int i = NUMBER_OF_VERTICES; i>0 ;i--) {
            if(!reverseGraph.isExplored.get(i)) {
                s = i;
                runDfs(reverseGraph, i, true, false);
            }
        }

        for(int i=NUMBER_OF_VERTICES; i>0; i--) {
            int key = finishingTimeLabelMap.get(i);
            if(!forwardGraph.isExplored.get(key)) {
                s = key;
                runDfs(forwardGraph, key, false, true);
            }
        }
        int[] leaderCount = new int[NUMBER_OF_VERTICES + 1];
        forwardGraph.getAdjacencyList().entrySet().forEach(entry -> {
            int index = entry.getValue().getLeader();
            int currentValue = leaderCount[index];
            leaderCount[index] = currentValue + 1;
        });
        Arrays.sort(leaderCount);

        System.out.println(leaderCount[NUMBER_OF_VERTICES]);
        System.out.println(leaderCount[NUMBER_OF_VERTICES-1]);
        System.out.println(leaderCount[NUMBER_OF_VERTICES-2]);
        System.out.println(leaderCount[NUMBER_OF_VERTICES-3]);
        System.out.println(leaderCount[NUMBER_OF_VERTICES-4]);
    }

    public void runDfs(DirectedGraph graph, Integer v, boolean computeForwardGraphFinishingTime, boolean computeConnectedSize) {
        graph.isExplored.put(v, true);
        DirectedGraph.Value value = graph.getAdjacencyList().get(v);
        value.setLeader(s);
        value.adjacencyList.forEach(toVertex -> {
            if(!graph.isExplored.get(toVertex)){
                runDfs(graph, toVertex, computeForwardGraphFinishingTime, computeConnectedSize);
            }
        });
        if(computeForwardGraphFinishingTime) {
            t++;
            value.setFinishingTime(t);
            finishingTimeLabelMap.put(t,v);
        }
        //set finishing time on forward graph as well
        if(computeForwardGraphFinishingTime && forwardGraph.getAdjacencyList().get(v) != null) {
            forwardGraph.getAdjacencyList().get(v).setFinishingTime(t);
        }
    }
}