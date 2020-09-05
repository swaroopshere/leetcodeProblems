package org.home.sshere.leetcode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumRoot = null;
        ListNode sumCurrentNode = null;
        ListNode firstNumber = l1;
        ListNode secondNumber = l2;
        int carry = 0;
        while(firstNumber != null || secondNumber != null) {
            int firstDigit = (firstNumber == null)? 0 : firstNumber.val;
            int secondDigit = (secondNumber == null)? 0 : secondNumber.val;

            int numberSum = firstDigit + secondDigit + carry;
            ListNode sum = new ListNode(numberSum%10);
            carry = numberSum/10;
            firstNumber = (firstNumber == null) ? null : firstNumber.next;
            secondNumber = (secondNumber == null) ? null : secondNumber.next;
            if(sumRoot == null)
                sumRoot = sum;
            if(sumCurrentNode == null)
                sumCurrentNode = sum;
            else {
                sumCurrentNode.next = sum;
                sumCurrentNode = sumCurrentNode.next;
            }
        }
        if(carry > 0) {
            ListNode sum = new ListNode(carry);
            sumCurrentNode.next = sum;
            sumCurrentNode = sumCurrentNode.next;
        }
        return sumRoot;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val;this.next = next;}
    }
}
