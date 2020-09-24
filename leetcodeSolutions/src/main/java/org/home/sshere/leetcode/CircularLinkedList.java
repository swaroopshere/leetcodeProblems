package org.home.sshere.leetcode;

public class CircularLinkedList {

    static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        Node currentNode = head;
        if(head == null) {
            head = newNode;
            newNode.next = head;
            return head;
        }
        Node nextNode = currentNode.next;
        if(nextNode == head) {
            currentNode.next = newNode;
            newNode.next = nextNode;
            return head;
        }

        //find min node
        Node minNode = head;
        while(true) {
            currentNode = currentNode.next;
            if(currentNode.val < minNode.val) {
                minNode = currentNode;
            }
            if(currentNode==head){
                break;
            }
        }
        currentNode = minNode;
        nextNode = currentNode.next;

        while(true) {
            if(currentNode.val > insertVal && insertVal <= nextNode.val && nextNode == minNode) {
                break;
            }
            if(currentNode.val <= insertVal && insertVal > nextNode.val && nextNode == minNode) {
                break;
            }
            if((currentNode.val <= insertVal && insertVal <= nextNode.val)) {
                break;
            }
            currentNode = currentNode.next;
            nextNode = nextNode.next;
        }
        currentNode.next = newNode;
        newNode.next = nextNode;
        return head;
    }
}
