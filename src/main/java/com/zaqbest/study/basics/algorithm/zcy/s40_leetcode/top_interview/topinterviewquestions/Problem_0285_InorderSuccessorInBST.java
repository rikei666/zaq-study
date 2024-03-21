package com.zaqbest.study.basics.algorithm.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 中序遍历的下一个节点
 *
 * morris遍历
 */
public class Problem_0285_InorderSuccessorInBST {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static TreeNode inorderSuccessor(TreeNode head, TreeNode p) {
		if (head == null) {
			return null;
		}
		TreeNode cur = head;
		TreeNode mostRight = null;
		TreeNode pre = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre == p) {
				return cur;
			} else {
				pre = cur;
			}
			cur = cur.right;
		}
		return null;
	}

}
