package org.algorithmsclass;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KosarajuTest {

    @Test
    public void testBuildGraph() throws IOException {
        new KosarajuAlgorithm().runDfsLoop();
    }
}
