package org.algorithmsclass.greedy;

public class BinaryTree {
    private Node root;

    public static class Node {
        private Integer value;
        private Node left;
        private Node right;

        public Node(Integer value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(Integer value) {
        Node current = root;
        Node parent = root;
        boolean isLeft = false;
        while(current!=null) {
            parent = current;
            if(value <= current.value) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
        }
        Node newNode = new Node(value);
        if(parent != null && isLeft) {
            parent.left = newNode;
        }
        if(parent != null && !isLeft){
            parent.right = newNode;
        }
    }

    public void delete (Integer value) {
        Node current = root;
        Node parent = root;

    }

    
}
