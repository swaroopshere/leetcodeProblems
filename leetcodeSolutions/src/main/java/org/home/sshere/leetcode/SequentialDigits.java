package org.home.sshere.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> sequence = new ArrayList<Integer>();
        int sequenceStart = 12;
        while(low > sequenceStart) {
            try {
                sequenceStart = increment(sequenceStart);
            } catch (NumberFormatException numberFormatException) {
                return List.of();
            }
        }
        while(sequenceStart <= high) {
            try {
                sequence.add(sequenceStart);
                sequenceStart = increment(sequenceStart);
            } catch (NumberFormatException numberFormatException) {
                break;
            }
        }
        return sequence;
    }

    private int increment(int number) {
        StringBuilder sb = new StringBuilder();
        int originalDigitSize = String.valueOf(number).length();
        if(number % 10 == 9) {
            for(int k=1; k<= originalDigitSize+1;k++) {
                //if(k<10)
                sb.append(k);
            }
            return Integer.parseInt(sb.toString());
        }

        for(int i=0;i<originalDigitSize;i++) {
            sb.append("1");
        }
        int incrementor = Integer.parseInt(sb.toString());
        int result = number+incrementor;
        /*int resultLength = String.valueOf(result).length();
        if( resultLength > originalDigitSize ) {
            sb = new StringBuilder();
            for(int j = 1;j<=resultLength;j++){
                sb.append(j);
            }
            return Integer.parseInt(sb.toString());
        } else {*/
            return result;
        //}
    }
}
