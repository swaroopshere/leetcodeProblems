package org.algorithmsclass.greedy;

import com.google.inject.internal.util.Lists;
import com.google.inject.internal.util.Maps;

import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<Integer, Integer> nodeLeaders;
    private Map<Integer, List<Integer>> componentsMap;

    public UnionFind(Integer numberOfNodes) {
        nodeLeaders = Maps.newHashMap();
        componentsMap = Maps.newHashMap();
        for(int i=1;i<=numberOfNodes;i++) {
            nodeLeaders.put(i,i);
            List<Integer> componentList = Lists.newArrayList(i);
            componentsMap.put(i, componentList);
        }
    }

    public Integer find(Integer node) {
        return nodeLeaders.get(node);
    }

    public void union(Integer vertex1, Integer vertex2) {
        // first, get leaders for both components
        int leader1 = nodeLeaders.get(vertex1);
        int leader2 = nodeLeaders.get(vertex2);

        if(leader1 != leader2) {
            //next, get all components for both vertices
            List<Integer> components1 = componentsMap.get(leader1);
            List<Integer> components2 = componentsMap.get(leader2);
            if(components1.size() >= components2.size()) {
                //components2 become children, update their leader to component1 leader
                components2.forEach(vertex -> nodeLeaders.put(vertex, leader1));
                //add all components2 to components1.
                components1.addAll(components2);
                //delete component2 from componentsMap
                //if(leader1!=leader2)
                componentsMap.remove(leader2);
            } else {
                //components1 become children, update their leader to component2 leader
                components1.forEach(vertex -> nodeLeaders.put(vertex, leader2));
                //add all components1 to components2.
                components2.addAll(components1);
                //delete component1 from componentsMap
                //if(leader1!=leader2)
                componentsMap.remove(leader1);
            }
        }
    }

    public int getComponentsSize() {
        return this.componentsMap.size();
    }
}
