package com.zaqbest.study.basics.algorithm.zcy.s40_leetcode.top_100_like.toplikedquestions;

public class Problem_0142_LinkedListCycleII {

	// 这个类不用提交
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

}
