package org.home.sshere.leetcode;

public class StringToNumber {
    public int myAtoi(String str) {
        if (str.length() == 0)
            return 0;
        StringBuilder sb = new StringBuilder();
        boolean numberStart = false;
        //boolean numberStop = true;
        boolean isNegative = false;
        boolean isPositive = false;
        for(int i=0;i<str.length();i++) {
            char currentChar = str.charAt(i);
            if(!numberStart) {
                if(currentChar == ' ') {
                    if(isNegative || isPositive)
                        break;
                    //whitespace
                } else if(currentChar == '-') {
                    if(isNegative)
                        break;
                    //negative
                    isNegative = true;
                    if(isPositive)
                        break;
                } else if(currentChar == '+') {
                    if(isPositive)
                        break;
                    isPositive = true;
                  if(isNegative)
                      break;
                } else if(isNumber(currentChar)) {
                    numberStart = true;
                    sb.append(currentChar);
                } else if(!isNumber(currentChar)) {
                    isNegative = false;
                    numberStart = false;
                    return 0;
                }
            } else {
                if(isNumber(currentChar)) {
                    sb.append(currentChar);
                } else {
                    numberStart = false;
                    break;
                }
            }
        }

        String finalString = sb.toString();
        if(finalString.length() == 0)
            return 0;
        int finalNumber = 0;
        try {
            finalNumber = Integer.parseInt(finalString);
        } catch (NumberFormatException nfe) {
            if(isNegative)
                finalNumber = Integer.MIN_VALUE;
            else
                finalNumber = Integer.MAX_VALUE;
        }
        if(isNegative && finalNumber != Integer.MIN_VALUE) {
            return finalNumber*-1;
        } else
            return finalNumber;
    }

    private boolean isNumber(char val) {
        if(val == '0' || val == '1' || val == '2' || val == '3' || val == '4' || val == '5' || val == '6' || val == '7' || val == '8' || val == '9')
            return true;
        else return false;
    }
}
