package org.algorithmsclass.kosaraju;

import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;
import java.util.List;
import java.util.Map;

public class DirectedGraph {
    public Map<Integer, Value> graph;
    public Map<Integer, Boolean> isExplored;

    public static class Value {
        private Integer finishingTime;
        private Integer leader;
        List<Integer> adjacencyList;


        public Value() {
            this.adjacencyList = Lists.newArrayList();
            this.finishingTime = null;
            this.leader = null;
        }

        public Value(List<Integer> adjacencyList) {
            this.adjacencyList = adjacencyList;
            this.finishingTime = null;
            this.leader = null;
        }
        /* getter methods */
        public Integer getFinishingTime() {return this.finishingTime;}
        public Integer getLeader() {return this.leader;}
        public List<Integer> getAdjacencyList() {return this.adjacencyList;}

        /* setter methods */
        public void setFinishingTime(Integer finishingTime) { this.finishingTime = finishingTime;}
        public void setLeader(Integer leader) {this.leader = leader;}
        public void setAdjacencyList(List<Integer> adjacencyList) {this.adjacencyList = adjacencyList;}
    }

    public DirectedGraph() {
        graph = Maps.newHashMap();
        isExplored = Maps.newHashMap();
    }

    public void addEdge(Integer fromVertex, Integer toVertex) {
        Value toVertexList;
        if(graph.containsKey(fromVertex)) {
            toVertexList = graph.get(fromVertex);
            toVertexList.adjacencyList.add(toVertex);
        } else {
            toVertexList = new Value(Lists.newArrayList(toVertex));
            graph.put(fromVertex, toVertexList);
        }
    }

    public Map<Integer, Value> getAdjacencyList() {
        return this.graph;
    }
}
