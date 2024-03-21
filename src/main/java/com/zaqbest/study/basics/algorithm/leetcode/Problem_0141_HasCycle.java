package com.zaqbest.study.basics.algorithm.leetcode;

/**
 * 141: 环形链表
 */
public class Problem_0141_HasCycle {

    public static class Node {
        int val;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }

    public boolean hasCycle(Node head) {
        if (head == null || head.next == null)
            return false;

        Node fast = head.next.next;
        Node slow = head.next;

        while (slow != fast){
            if (fast == null || fast.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        }

        return true;
    }
}
