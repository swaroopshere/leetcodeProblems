package org.swaroopshere.personal.leetcode;

import java.util.HashSet;
import java.util.Set;

public class JewelStones {
    public static int numJewelsInStones(String J, String S) {
        int numJewelsInStones = 0;
        Set<Character> jewelSet = new HashSet<Character>();
        for(char a : J.toCharArray()){
            jewelSet.add(a);
        }

        for(char b: S.toCharArray()){
            if(jewelSet.contains(b)){
                numJewelsInStones ++;
            }
        }

        return numJewelsInStones;
    }
}
