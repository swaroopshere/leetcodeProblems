package org.algorithmsclass.ds;

import com.google.inject.internal.util.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MedianMaintenance {
    private PriorityQueue<Integer> lowers;
    private PriorityQueue<Integer> highers;
    private List<Integer> integerList;

    public MedianMaintenance() throws IOException {
        integerList = getIntegerList();
        //Max Heap
        lowers = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return -1*t1.compareTo(t2);
            }
        });

        //Min Heap
        highers = new PriorityQueue<>();
    }

    public void generateMedianList() {
        int moduloSum = 0;
        for (Integer num : integerList) {
            //add number to heaps
            addNumber(num);
            //rebalance heaps
            rebalanceHeaps();
            //calculate median
            moduloSum+=calculateMedian();
        };
        int mod = moduloSum%10000;
        System.out.println("Modulo sum 10000 = " + mod);
    }

    private int calculateMedian() {
        PriorityQueue<Integer> bigger = (lowers.size() >= highers.size()) ? lowers : highers;
        //PriorityQueue<Integer> smaller = (lowers.size() < highers.size()) ? lowers : highers;
        //if sizes are equal, get average, else get larger
        if(lowers.size() == highers.size()) {
            //int num1 = bigger.peek();
            //int num2 = smaller.peek();
            return lowers.peek();
            //return ((double)(bigger.peek() + smaller.peek())/2);
        } else {
            return bigger.peek();
        }
        //return lowers.peek();
    }

    private void rebalanceHeaps() {
        PriorityQueue<Integer> bigger = (lowers.size() >= highers.size()) ? lowers : highers;
        PriorityQueue<Integer> smaller = (lowers.size() < highers.size()) ? lowers : highers;

        if(bigger.size() - smaller.size() > 1) {
            smaller.add(bigger.poll());
        }
    }

    private void addNumber(Integer number) {
        //get highest number from lowers
        Integer lowerMax = lowers.peek();
        if(lowers.size() == 0 || number < lowerMax)
            lowers.add(number);
        else
            highers.add(number);

    }

    private List<Integer> getIntegerList() throws IOException {
        List<Integer> integerList = Lists.newArrayList();
        Path filePath = Paths.get("c:\\Users\\swaro_000\\Documents\\git\\leetcodeProblems\\ProgrammingExercises\\Median.txt");
        Files.lines(filePath).forEach(word -> integerList.add(Integer.parseInt(word)));
        return integerList;
    }
}
