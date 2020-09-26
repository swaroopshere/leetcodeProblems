package org.home.sshere.leetcode;

import org.algorithmsclass.divideConquer.CountInversions;
import org.algorithmsclass.divideConquer.QuickSort;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeTests {

    @Test
    public void testReverseNumber() {
        assertEquals(11111,LeetCodeProblems.reverse(11111));
        assertEquals(1234,LeetCodeProblems.reverse(4321));
        assertEquals(12345,LeetCodeProblems.reverse(54321));
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(LeetCodeProblems.isPalindrome(121));
        assertFalse(LeetCodeProblems.isPalindrome(-121));
        assertFalse(LeetCodeProblems.isPalindrome(1999999999));

    }

    @Test
    public void testRomanToInt() {
        //assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
    }

    @Test
    public void testKaratsuba() {
        //BigDecimal result = LeetCodeProblems.karatsuba(BigDecimal.valueOf(3141592653589793238462643383279502884197169399375105820974944592D), BigDecimal.valueOf(2718281828459045235360287471352662497757247093699959574966967627D));
        BigInteger result = AlgorithmsClassExercises.karatsuba(new BigInteger("3141592653589793238462643383279502884197169399375105820974944592"), new BigInteger("2718281828459045235360287471352662497757247093699959574966967627"));
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void testMergeSort() {
        int[] array = new int[]{5,3,1,7,9,45,22,56,21,90,76,31,22,41,56,12,78};
        int[] result = AlgorithmsClassExercises.mergeSort(array);
        int[] expectedResult = new int[] {1,3,5,7,9,12,21,22,22,31,41,45,56,56,76,78,90};
        assertTrue(Arrays.equals(expectedResult, result));
    }

    @Test
    public void testSumLL() {
        AddTwoNumbers.ListNode l1 = new AddTwoNumbers.ListNode(2);
        AddTwoNumbers.ListNode l12 = new AddTwoNumbers.ListNode(4);
        AddTwoNumbers.ListNode l13 = new AddTwoNumbers.ListNode(3);
        l1.next = l12;
        l12.next = l13;
        AddTwoNumbers.ListNode l2 = new AddTwoNumbers.ListNode(5);
        AddTwoNumbers.ListNode l22 = new AddTwoNumbers.ListNode(6);
        AddTwoNumbers.ListNode l23 = new AddTwoNumbers.ListNode(4);
        l2.next = l22;
        l22.next = l23;
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        AddTwoNumbers.ListNode l3 = addTwoNumbers.addTwoNumbers(l1, l2);
        assertNotNull(l3);

    }

    @Test
    public void testSumLLCarry() {
        AddTwoNumbers.ListNode l1 = new AddTwoNumbers.ListNode(5);
        AddTwoNumbers.ListNode l2 = new AddTwoNumbers.ListNode(5);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        AddTwoNumbers.ListNode l3 = addTwoNumbers.addTwoNumbers(l1, l2);
        assertNotNull(l3);

    }

    @Test
    public void testMedian1() {
        //assertEquals(2, AlgorithmsClassExercises.medianTwoArrays(new int[]{1,3}, new int[]{2}));
        //assertEquals(2.5, AlgorithmsClassExercises.medianTwoArrays(new int[]{1,2}, new int[]{3,4}));
        assertEquals(1, AlgorithmsClassExercises.medianTwoArrays(new int[]{}, new int[]{1}));
    }

    @Test
    public void testMatrixMultiply() {
        int[][] first = {{2,1},{3,2},{1,1}};
        int[][] second = {{1,-2,1},{2,1,3}};
        int[][] result = {{4,-3,5},{7,-4,9},{3,-1,4}};
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(AlgorithmsClassExercises.matrixMultiplication(first, second)));
    }

    @Test
    public void testInversionsCount() throws IOException {
        System.out.println(CountInversions.countInversions());
    }

    @Test
    public void testFindMax() {
        assertEquals(455,AlgorithmsClassExercises.findLargest(new int[]{3,6,4,1,5,7,87,43,12,455,65,21,88,54,32,11}));
    }

    @Test
    public void testQuickSort() throws IOException {
        assertTrue(QuickSort.areNumbersSorted(QuickSort.quickSortEx1()));
    }

    @Test
    public void testMedianCheck() {
        int[] array1 = new int[] {2, 20, 1, 15, 3, 11, 13, 6, 16, 10, 19, 5, 4, 9, 8, 14, 18, 17, 7, 12};
        assertEquals(9, QuickSort.choosePivotPosition(array1,0,19));
        int[] array2 = new int[] {7, 1, 3, 6, 2, 5, 4, 9, 8};
        assertEquals(0, QuickSort.choosePivotPosition(array2,0,8));
        int[] array3 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        assertEquals(9, QuickSort.choosePivotPosition(array3,0,19));
        int[] array4 = new int[] {20,19};
        assertEquals(0, QuickSort.choosePivotPosition(array4,0,1));
        int[] array5 = new int[] {18, 17, 20, 19};
        assertEquals(0, QuickSort.choosePivotPosition(array5,0,3));
        int[] array6 = new int[] {12, 11, 13, 15, 14};
        assertEquals(2, QuickSort.choosePivotPosition(array6,0,4));
        int[] array7 = new int[] {4,1,3,6,2};
        assertEquals(2, QuickSort.choosePivotPosition(array7,0,4));
    }

    @Test
    public void testRobotBounded() {
        //assertTrue(RobotDirections.isRobotBounded("GGLLGG"));
        assertTrue(RobotDirections.isRobotBounded("LRRRRLLLRL"));
    }

    @Test
    public void testStockPrice() {
        int[] prices = new int[]{7,1,5,3,6,4};
        assertEquals(5,new StockPrice().maxProfit(prices));
    }

    @Test
    public void testSequentialDigits() {
        List<Integer> sequence = new SequentialDigits().sequentialDigits(178546104,812704742);
        assertEquals(List.of(), sequence);
    }

    @Test
    public void testCircularLL() {
        CircularLinkedList.Node node1 = new CircularLinkedList.Node(3);
        CircularLinkedList.Node node2 = new CircularLinkedList.Node(5);
        CircularLinkedList.Node node3 = new CircularLinkedList.Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        CircularLinkedList.Node head = new CircularLinkedList().insert(node1, 0);
        assertNotNull(head);
    }

    @Test
    public void testGasStation(){
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};
        assertEquals(3, new CarTravel().canCompleteCircuit(gas, cost));
        assertEquals(-1, new CarTravel().canCompleteCircuit(new int[]{3,3,4}, new int[]{3,4,4}));
    }

    @Test
    public void testStringToNumber() {
        assertEquals(-42,new StringToNumber().myAtoi("    -42"));
    }

}
