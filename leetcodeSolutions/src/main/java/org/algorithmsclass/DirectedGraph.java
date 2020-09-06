package org.home.sshere.algorithmsclass;

import com.google.inject.internal.util.Maps;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class DirectedGraph {
    private Map<BigInteger, List<BigInteger>> adjacencyList;

    public DirectedGraph() {
        adjacencyList = Maps.newHashMap();
    }

    public DirectedGraph(Map<BigInteger, List<BigInteger>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void addEdge(BigInteger fromVertex, BigInteger toVertex) {
        if(adjacencyList.containsKey(fromVertex)) {
            adjacencyList.get(fromVertex).stream()
        }
    }
    public Map<BigInteger, List<BigInteger>> getAdjacencyList() {
        return this.adjacencyList;
    }
}
