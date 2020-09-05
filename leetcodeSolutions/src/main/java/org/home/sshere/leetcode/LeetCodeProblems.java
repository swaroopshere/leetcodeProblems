package org.home.sshere.leetcode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeProblems {
    //reverse 32 bit signed Integer
    public static int reverse(int x) {
        int reversedNumber = 0;
        int count = 0;
        while(x != 0) {
            int remainder = x % 10;
            if(reversedNumber > Integer.MAX_VALUE/10)
                return 0;
            if(reversedNumber < Integer.MIN_VALUE/10)
                return 0;
            reversedNumber = (10 * reversedNumber) + remainder;

            x = x/10;
            count++;
        }
        return reversedNumber;
    }

    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int y = reverse(x);
        if (x == y)
            return true;
        else
            return false;
    }

    public static int[] numSum(int[] nums, int target) {
        int[] twoNums = new int[2];
        //map (value from array, index)
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i=0; i< nums.length; i++) {
            numMap.put(nums[i], i);
        }
        for(int i=0; i< nums.length; i++) {
            int secondNum = target - nums[i];
            Integer secondNumPosition = numMap.get(secondNum);
            if(secondNumPosition != null && i != secondNumPosition.intValue()) {
                twoNums[0] = i;
                twoNums[1] = secondNumPosition.intValue();
            }
        }
        return twoNums;
    }

}
