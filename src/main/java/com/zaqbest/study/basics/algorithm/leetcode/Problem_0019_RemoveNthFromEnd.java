package com.zaqbest.study.basics.algorithm.leetcode;

public class Problem_0019_RemoveNthFromEnd {

    public static void main(String[] args) {
    }
    public static Node removeNthFromEnd(Node head, int n) {

        if (head == null || n < 0){
            return head;
        }

        Node dummyNode = new Node();
        dummyNode.next = head;

        Node first = head;
        Node second = dummyNode;//n-1

        while (n-- > 0 && first != null){
            first = first.next;
        }

        //链表长度不够N
        if (n > 0 ){
            return head;
        }

        while (first != null){
            first = first.next;
            second = second.next;
        }

        //链表删除
        second.next = second.next.next;

        return dummyNode.next;
    }

    public static class Node {
        int val;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }
}
