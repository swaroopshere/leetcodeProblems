package org.algorithmsclass;

import org.algorithmsclass.greedy.SchedulingProblemExercises;
import org.algorithmsclass.kosaraju.KosarajuAlgorithm;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class KosarajuTest {
    @Test
    public void testBuildGraph() throws IOException {
        new KosarajuAlgorithm().runDfsLoop();
    }

    @Test
    public void testSchedulingProblemOne() throws IOException, URISyntaxException {
        new SchedulingProblemExercises().getOptimizedValue();
    }
}
