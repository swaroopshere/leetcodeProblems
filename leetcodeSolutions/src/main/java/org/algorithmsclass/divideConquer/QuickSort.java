package org.algorithmsclass.divideConquer;

import java.io.IOException;
import java.util.Arrays;

public class QuickSort {
    public static int comparisonCount = 0;

    public static int[] quickSortEx1() throws IOException {
        //int[] numbers = Util.readIntegersFromFile("C:\\Users\\swaro_000\\Documents\\git\\ProgrammingExercises\\QuickSort.txt");
        int[] numbers = new int[] { 2, 20, 1, 15, 3, 11, 13, 6, 16, 10, 19, 5, 4, 9, 8, 14, 18, 17, 7, 12 };
        quickSortRecursive(numbers, 0, numbers.length-1);
        //comparisonCount += numbers.length-1;
        System.out.println("Comparisons = " + comparisonCount);
        return numbers;
    }

    public static void quickSortRecursive(int[] numbers, int leftPosition, int rightPosition) {
        if(rightPosition - leftPosition == 0) {
            return;
        }
        int pivotPosition = partition3(numbers, leftPosition, rightPosition);
        /*if (leftPosition > pivotPosition - 1)
            pivotPosition = pivotPosition + 1;*/
        if(leftPosition <= pivotPosition-1) {
            quickSortRecursive(numbers, leftPosition, pivotPosition - 1);
            comparisonCount += (pivotPosition - 1 - leftPosition);
            System.out.println("left=" + leftPosition + " , right is " + rightPosition);
            System.out.println("count=" + comparisonCount);
        }
        /*if (pivotPosition + 1 > rightPosition)
            pivotPosition = pivotPosition-1;*/
        if(pivotPosition+1 <= rightPosition) {
            quickSortRecursive(numbers, pivotPosition + 1, rightPosition);
            comparisonCount += (rightPosition - (pivotPosition + 1));
            System.out.println("left=" + leftPosition + " , right is " + rightPosition);
            System.out.println("count=" + comparisonCount);
        }
        //comparisonCount+=(rightPosition-(leftPosition+1));

    }

    public static int partition(int[] numbers, int startPosition, int endPosition) {
        int pivotPosition = startPosition;
        int i = startPosition + 1;
        int j = startPosition + 1;
        while (j <= endPosition) {
            if(numbers[j] < numbers[pivotPosition]) {
                int temp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = temp;
                i++;
            }
            j++;
        }
        //swap pivot and i-1
        int temp = numbers[pivotPosition];
        numbers[pivotPosition] = numbers[i-1];
        numbers[i-1] = temp;
        //comparisonCount+=endPosition-startPosition;
        return i-1;
    }

    public static int partition2(int[] numbers, int startPosition, int endPosition) {
        //swap start and end positions
        int temp = numbers[startPosition];
        numbers[startPosition] = numbers[endPosition];
        numbers[endPosition] = temp;
        return partition(numbers, startPosition, endPosition);
    }

    public static int partition3(int[] numbers, int startPosition, int endPosition) {
        //get pivotPosition
        int pivotPosition = choosePivotPosition(numbers, startPosition, endPosition);
        //swap pivot with start
        int temp = numbers[startPosition];
        numbers[startPosition] = numbers[pivotPosition];
        numbers[pivotPosition] = temp;
        return partition(numbers, startPosition, endPosition);
    }

    public static int choosePivotPosition(int[] numbers, int startPosition, int endPosition) {
        if(endPosition-startPosition == 1)
            return startPosition;
        int medianPosition = (endPosition+startPosition)/2;
        if((numbers[startPosition] <= numbers[medianPosition] && numbers[medianPosition] <= numbers[endPosition]) || (numbers[endPosition] <= numbers[medianPosition] && numbers[medianPosition] <= numbers[startPosition]))
            return medianPosition;
        else if ((numbers[medianPosition] <= numbers[startPosition] && numbers[startPosition] <= numbers[endPosition]) || (numbers[endPosition] <= numbers[startPosition] && numbers[startPosition] <= numbers[medianPosition]))
            return startPosition;
        else
            return endPosition;
    }

    public static boolean areNumbersSorted(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
        boolean sorted = true;
        for(int i=0; i<numbers.length-1;i++) {
            if(numbers[i] > numbers[i+1]) {
                sorted = false;
                System.out.println("Unsorted at " + i);
                //break;
            }
        }
        return sorted;
    }

}
