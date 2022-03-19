package com.zaqbest.study.foundation.alg.common;

public class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}
