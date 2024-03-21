package com.zaqbest.study.basics.algorithm.leetcode;


import java.util.Stack;

/**
 * 234: 回文链表
 *
 * 算法描述：
 * 使用栈记录倒序的情况 空间复杂度O(n)
 */
public class Problem_0234_IsPalindrome {
    private static class Node {
        int val;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
    }
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;
        Stack<Integer> stack = new Stack<>();
        Node p =head;
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }

        p = head;
        while (p != null){
            if (p.val != stack.pop())
                return false;
            p = p.next;
        }
        return true;
    }
}
