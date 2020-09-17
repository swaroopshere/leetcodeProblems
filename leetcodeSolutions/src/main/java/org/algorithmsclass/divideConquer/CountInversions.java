package org.algorithmsclass.divideConquer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountInversions {
    public static class InversionDao {
        long numberOfInversions;
        List<Long> listOfNumbers;
        public InversionDao(){}
        public InversionDao(long numberOfInversions, List<Long> listOfNumbers) {
            this.numberOfInversions = numberOfInversions;
            this.listOfNumbers = listOfNumbers;
        }
    }

    public static long countInversions() throws IOException{
        List<Long> numbers = readIntegersFromFile();
        return recursiveInversion(numbers).numberOfInversions;
    }
    public static InversionDao recursiveInversion(List<Long> numbers) {
        long size = numbers.size();
        if(size == 1) {
            return new InversionDao(0, numbers);
        }

        long half = size/2-1;
        List<List<Long>> splitNumbers = new ArrayList(numbers.stream().collect(Collectors.partitioningBy(s -> numbers.indexOf(s) > half)).values());
        InversionDao left = recursiveInversion(splitNumbers.get(0));
        InversionDao right = recursiveInversion(splitNumbers.get(1));
        return mergeAndCount(left, right);
    }

    private static InversionDao mergeAndCount(InversionDao left, InversionDao right) {
        List<Long> leftList = left.listOfNumbers;
        List<Long> rightList = right.listOfNumbers;
        List<Long> mergedList = new ArrayList<>();
        long inversionCount = left.numberOfInversions + right.numberOfInversions;
        int i = 0, j=0;
        while(i < leftList.size() && j < rightList.size()) {
            if(leftList.get(i) <= rightList.get(j)) {
                mergedList.add(leftList.get(i));
                i++;
            } else {
                mergedList.add(rightList.get(j));
                inversionCount+=(leftList.size()-i);
                j++;
            }
        }
        if(i < leftList.size()) {
            mergedList.addAll(leftList.subList(i,leftList.size()));
            //inversionCount+=(rightList.size()-j);
        }
        if(j< rightList.size()) {
            mergedList.addAll(rightList.subList(j,rightList.size()));
        }
        return new InversionDao(inversionCount, mergedList);
    }

    private static List<Long> readIntegersFromFile() throws IOException {
        List<Long> numbers = new ArrayList();
        Path path = Paths.get("C:\\Users\\swaro_000\\Documents\\ProgrammingExercises\\IntegerArray.txt");
        numbers = Files.lines(path).map(number -> Long.valueOf(number)).collect(Collectors.toList());
        return numbers;
    }
}
