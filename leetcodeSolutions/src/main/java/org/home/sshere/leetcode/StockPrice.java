package org.home.sshere.leetcode;

public class StockPrice {
    public int maxProfit(int[] prices) {
        if(prices.length < 2 )
            return 0;
        int length = prices.length;
        int nextPowerOfTwo = nextPowerOf2(length);
        int[] updatedPrices = new int[nextPowerOfTwo];
        for(int i=0;i<length;i++) {
            updatedPrices[i] = prices[i];
        }
        for(int i=length;i<nextPowerOfTwo;i++){
            updatedPrices[i] = prices[length-1];
        }
        length = updatedPrices.length;
        int profit = recursiveMax(updatedPrices);
        if(profit < 0)
            return 0;
        else return profit;
    }

    private int recursiveMax(int[] prices) {
        if(prices.length == 2) {
            return prices[1]-prices[0];
        }
        int midPoint = prices.length/2-1;
        int[] leftSubArray = new int[midPoint+1];
        int[] rightSubArray = new int[midPoint+1];
        System.arraycopy(prices, 0, leftSubArray, 0, midPoint+1);
        System.arraycopy(prices, midPoint+1, rightSubArray, 0, prices.length-1-midPoint);
        int leftOptimal = recursiveMax(leftSubArray);
        int rightOptimal = recursiveMax(rightSubArray);
        int price = findMax(rightSubArray) - findMin(leftSubArray);
        return Math.max(Math.max(leftOptimal, rightOptimal), price);
    }

    private int findMin(int[] leftSubArray) {
        int min = leftSubArray[0];
        for(int i=0; i< leftSubArray.length; i++) {
            if(leftSubArray[i] < min) {
                min = leftSubArray[i];
            }
        }
        return min;
    }

    private int findMax(int[] rightSubArray) {
        int max = rightSubArray[0];
        for(int i=0; i< rightSubArray.length; i++) {
            if(rightSubArray[i] > max) {
                max = rightSubArray[i];
            }
        }
        return max;
    }

    private int nextPowerOf2(int n) {
        int count = 0;
        // First n in the below
        // condition is for the
        // case where n is 0
        if (n > 0 && (n & (n - 1)) == 0)
            return n;
        while(n != 0) {
            n >>= 1;
            count += 1;
        }
        return 1 << count;
    }
}
