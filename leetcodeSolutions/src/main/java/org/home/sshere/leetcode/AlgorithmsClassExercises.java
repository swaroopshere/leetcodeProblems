package org.home.sshere.leetcode;

import java.math.BigInteger;
import java.util.Arrays;

public class AlgorithmsClassExercises {

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        return recursiveMultiply(x,y);
    }

    public static float medianTwoArrays(int[] left, int[] right) {
        float median;
        int[] merged = merge(left, right);
        int mergedLength = merged.length;
        if(mergedLength%2 == 0) {
            median = ((float)merged[mergedLength/2-1] + (float)merged[mergedLength/2])/2;
        } else {
            median = (float)merged[mergedLength/2];
        }
        return median;
    }

    public static int[] mergeSort(int[] array) {
        int arrayLength = array.length;
        if(arrayLength <= 1)
            return array;
        int arrayMid = arrayLength/2;
        int[] leftMerge = mergeSort(Arrays.copyOfRange(array, 0, arrayMid));
        int[] rightMerge = mergeSort(Arrays.copyOfRange(array, arrayMid, arrayLength));
        return merge(leftMerge, rightMerge);
    }

    public static int findLargest(int[] numbers) {
        int length = numbers.length;
        if(length <= 2) {
            return Math.max(numbers[0], numbers[1]);
        }
        int largestLeft = findLargest(Arrays.copyOfRange(numbers, 0,length/2));
        int largestRight = findLargest(Arrays.copyOfRange(numbers, length/2, length));
        return Math.max(largestLeft, largestRight);
    }

    private static int[] merge(int[] left, int[] right) {
        int i=0, j=0, k=0;
        int[] mergedArray = new int[left.length + right.length];

        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                mergedArray[k] = left[i];
                i++;
            } else {
                mergedArray[k] = right[j];
                j++;
            }
            k++;
        }
        if(i < left.length) {
            while(i < left.length) {
                mergedArray[k] = left[i];
                k++;
                i++;
            }
        } else if(j < right.length) {
            while(j < right.length) {
                mergedArray[k] = right[j];
                k++;
                j++;
            }
        }
        return mergedArray;
    }

    private static BigInteger recursiveMultiply(BigInteger x, BigInteger y) {
         int x_size = getDecimalLength(x);
         int y_size = getDecimalLength(y);
         if(x_size <= 1 || y_size <= 1) {
             return x.multiply(y);
         }
         int size = Math.max(x_size, y_size);
         int m = size/2;
         BigInteger divider = new BigInteger("10").pow(m);
        BigInteger x_h = x.divide(divider);
        BigInteger x_l = x.remainder(divider);
        BigInteger y_h = y.divide(divider);
        BigInteger y_l = y.remainder(divider);

        BigInteger a = recursiveMultiply(x_h, y_h);
        BigInteger d = recursiveMultiply(x_l, y_l);
        BigInteger e = recursiveMultiply(x_h.add( x_l), y_h.add(y_l)).subtract(a).subtract(d);

        BigInteger multipliComponent = new BigInteger("10").pow(m*2);

         return (a.multiply(multipliComponent).add(e.multiply(divider)).add(d));
    }

    private static int getDecimalLength(BigInteger number) {
         return number.toString().length();
    }

    public static int[][] matrixMultiplication(int[][] first, int [][] second) {
        int firstRowCount = first.length;
        int firstColCount = first[0].length;
        int secondRowCount = second.length;
        int secondColCount = second[0].length;
        int[][] result = new int[firstRowCount][secondColCount];
        for(int i=0; i<firstRowCount; i++) {
            for(int j=0;j<secondColCount; j++) {
                for(int k=0; k<firstColCount; k++) {
                    result[i][j]+=first[i][k]*second[k][j];
                }
            }
        }
        return result;
    }
}
