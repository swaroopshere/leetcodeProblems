package org.algorithmsclass;

import com.google.inject.internal.util.Maps;

import java.io.IOException;
import java.util.*;

public class KosarajuAlgorithm {
    public Integer t = 0;
    public Integer s = null;
    public DirectedGraph reverseGraph;
    public DirectedGraph forwardGraph;
    //public Integer connectedSize = 0;
    Map<Integer, Integer> finishingTimeLabelMap = Maps.newHashMap();
    //PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
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
                //maxHeap.add(connectedSize);
                //connectedSize = 0;
            } /*else {
                maxHeap.add(connectedSize);
                connectedSize = 0;
            }*/
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
        //System.out.println(maxHeap.remove());
        //System.out.println(maxHeap.remove());
    }

    public void runDfs(DirectedGraph graph, Integer v, boolean computeForwardGraphFinishingTime, boolean computeConnectedSize) {
        graph.isExplored.put(v, true);
        DirectedGraph.Value value = graph.getAdjacencyList().get(v);
        //if(value != null) {
        value.setLeader(s);
        value.adjacencyList.forEach(toVertex -> {
            if(!graph.isExplored.get(toVertex)){
                runDfs(graph, toVertex, computeForwardGraphFinishingTime, computeConnectedSize);
                /*if(computeConnectedSize)
                    connectedSize++;*/
            }
        });
        //}
        /*else {
            //get rid of Null values for Value
            value = new DirectedGraph.Value();
            value.setAdjacencyList(Lists.newArrayList());
            graph.getAdjacencyList().put(v,value);
        }*/
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
