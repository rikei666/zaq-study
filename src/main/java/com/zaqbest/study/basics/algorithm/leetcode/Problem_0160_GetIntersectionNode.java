package com.zaqbest.study.basics.algorithm.leetcode;


/**
 * 160: 相交链表
 */
public class Problem_0160_GetIntersectionNode {
    public static class Node {
        int val;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }

    public Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null)
            return null;

        int aLen = 0;
        int bLen = 0;
        Node pA = headA;
        Node pB = headB;
        while (pA != null) {
            pA = pA.next;
            aLen++;
        }

        while (pB != null){
            pB = pB.next;
            bLen++;
        }

        //最后一个节点不一样，没有交叉
        if (pA != pB) return null;

        pA = headA;
        pB = headB;
        if (aLen > bLen){
            for(int i = 0; i < aLen - bLen;i++)
                pA = pA.next;
        } else if (bLen > aLen){
            for(int i = 0; i < bLen - aLen;i++)
                pB = pB.next;
        }
        while (pA != pB){
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }
}
