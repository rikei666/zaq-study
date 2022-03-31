package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0237_DeleteNodeInLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

}
