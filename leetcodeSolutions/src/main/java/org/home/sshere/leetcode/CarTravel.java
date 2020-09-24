package org.home.sshere.leetcode;

public class CarTravel {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length != cost.length)
            throw new IllegalStateException("Incorrect gas and cost information");
        int startCount = 0;
        int successCount = 0;
        int length = gas.length;
        int startPosition = 0;
        int currentPosition = startPosition;
        int currentTank = 0;
        boolean noRouteExists = false;
        while(startCount < length && successCount < length) {
            currentTank+=gas[currentPosition];
            if(currentTank >= cost[currentPosition]) {
                currentTank-=cost[currentPosition];
                currentPosition++;
                successCount++;
                if(currentPosition == length)
                    currentPosition = 0;
                noRouteExists = false;
            } else {
                startPosition++;
                currentPosition = startPosition;
                if(startPosition == length) {
                    startPosition = 0;
                }
                noRouteExists = true;
                startCount++;
                currentTank = 0;
                successCount = 0;
            }
        }
        if(noRouteExists) {
            return -1;
        } else {
            return startPosition;
        }
    }
}
