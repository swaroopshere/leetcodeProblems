package org.algorithmsclass;

import org.algorithmsclass.ds.MedianMaintenance;
import org.algorithmsclass.greedy.DikjstraAlgorithm;
import org.algorithmsclass.greedy.KruskalsAlgorithm;
import org.algorithmsclass.greedy.PrimsAlgorithm;
import org.algorithmsclass.greedy.SchedulingProblemExercises;
import org.algorithmsclass.kosaraju.KosarajuAlgorithm;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class AlgorithmsClassTest {
    @Test
    public void testBuildGraph() throws IOException {
        new KosarajuAlgorithm().runDfsLoop();
    }

    @Test
    public void testSchedulingProblemOne() throws IOException, URISyntaxException {
        new SchedulingProblemExercises().getOptimizedValue();
    }

    @Test
    public void testPrims() throws IOException {
        new PrimsAlgorithm().buildMST();
    }

    @Test
    public void testKruskals() throws IOException {
        new KruskalsAlgorithm().calculate();
    }

    @Test
    public void testDijkstra() throws IOException {
        new DikjstraAlgorithm().calculate();
    }

    @Test
    public void testMedianMaintenance() throws IOException {
        new MedianMaintenance().generateMedianList();
    }
}
