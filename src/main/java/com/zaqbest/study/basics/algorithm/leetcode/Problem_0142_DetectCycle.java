package com.zaqbest.study.basics.algorithm.leetcode;


/**
 * 142: 环形链表II
 *
 *
 * <p>{@link Problem_0141_HasCycle#hasCycle(Node)} )}
 *
 * <p>
 *     算法描述：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 * </p>
 */
public class Problem_0142_DetectCycle {
    public static class Node {
        int val;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next) { this.val = val; this.next = next; }
    }

    public Node detectCycle(Node head) {
        if (head == null || head.next == null)
            return null;

        Node fast = head.next.next;
        Node slow = head.next;

        while (slow != fast){
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}
