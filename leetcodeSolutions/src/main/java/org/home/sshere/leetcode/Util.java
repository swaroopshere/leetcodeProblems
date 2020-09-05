package org.home.sshere.leetcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static int[] mergeSort(int[] array) {
        int arrayLength = array.length;
        if(arrayLength <= 1)
            return array;
        int arrayMid = arrayLength/2;
        int[] leftMerge = mergeSort(Arrays.copyOfRange(array, 0, arrayMid));
        int[] rightMerge = mergeSort(Arrays.copyOfRange(array, arrayMid, arrayLength));
        return merge(leftMerge, rightMerge);
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

    public static int[] readIntegersFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.lines(path).mapToInt(number -> Integer.parseInt(number)).toArray();
    }
}
