package org.swaroopshere.personal.leetcode.test;


import org.junit.jupiter.api.Test;
import org.swaroopshere.personal.leetcode.JewelStones;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JewelStonesTest {

    @Test
    public void testSimplePath() {
        assertEquals(3, JewelStones.numJewelsInStones("aA","aAAbbbb"));
    }

    @Test
    public void testNoJewels() {
        assertEquals(0, JewelStones.numJewelsInStones("z","ZZ"));
    }

}
