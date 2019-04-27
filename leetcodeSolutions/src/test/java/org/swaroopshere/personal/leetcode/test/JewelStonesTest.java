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

    @Test
    public void testEmptyJewels() {
        assertEquals(0, JewelStones.numJewelsInStones("","ZZ"));
    }

    @Test
    public void testNullStones() {
        assertEquals(0, JewelStones.numJewelsInStones("aEw",null));
    }

    @Test
    public void testNullJewels() {
        assertEquals(0, JewelStones.numJewelsInStones(null,"ZZ"));
    }

}
