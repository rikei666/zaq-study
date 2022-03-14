package com.zaqbest.study.alg.zcy.basic.zdemo.class02;

public class Code02_DeleteGivenValue_Study {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);
        head.next.next.next.next.next = new Node(2);

        head = removeValue(head, 2);

        while (head!=null){
            System.out.println(head.value + " ");
            head = head.next;
        }
    }
}
