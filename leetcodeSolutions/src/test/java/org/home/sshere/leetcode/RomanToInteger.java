package org.home.sshere.leetcode;

import java.util.Map;

public class RomanToInteger {
    public static int romanToInt(String s) {
        Map<String, Integer> romanToIntMap = Map.of("I", 1,
                "V", 5,
                "X", 10,
                "L", 50,
                "C", 100,
                "D", 500,
                "M", 1000);
        int number = 0;
        boolean subTractFlag = false;
        for(int i=0; i < s.length(); i++) {
            char current = s.charAt(i);
            char next = (i >= s.length()-1) ? ' ' : s.charAt(i + 1);
            if(subTractFlag) {
                int inter = romanToIntMap.get(String.valueOf(current)) - romanToIntMap.get(String.valueOf(s.charAt(i-1)));
                number+= inter;
                subTractFlag = false;
            }else if((current == 'I' && (next == 'V' || next =='X')) ||
                    (current == 'X' && (next == 'L' || next == 'C')) ||
                    (current == 'C' && (next == 'D' || next == 'M'))) {
                //subtract next operation
                subTractFlag = true;
            }  else {
                //add with next
                subTractFlag = false;
                number += romanToIntMap.get(String.valueOf(current));
            }
        }
        return number;
    }
}
