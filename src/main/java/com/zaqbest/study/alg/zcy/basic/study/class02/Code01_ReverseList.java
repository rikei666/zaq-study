package com.zaqbest.study.alg.zcy.basic.study.class02;

public class Code01_ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 单向链表翻转
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null){
            next = head.next;
            head.next = pre;
            //走到下一步
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 双向链表翻转
     *
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;//比单向链表只是多了一次指针操作
            pre = head;
            head = next;
        }
        return pre;
    }
}
