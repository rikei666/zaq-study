package com.zaqbest.study.foundation.alg.zcy.s10_primary.class07;

import java.util.Stack;

/**
 * 非递归遍历
 */
public class Code02_UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 先序遍历
	 *
	 * 基于栈实现
	 * - 压入头结点
	 * - 弹出并访问，访问，压入右树，压入左树
	 * @param head
	 */
	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.add(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	public static void pre_study(Node head) {
		System.out.println("pre order:");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(head);

			while (!stack.isEmpty()) {
				head = stack.pop();

				System.out.println(head.value);

				if (head.right != null) {
					stack.push(head.right);
				}

				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
	}

	/**
	 * 中序遍历
	 *
	 * @param head
	 */
	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
		System.out.println();
	}

	/**
	 * 后续遍历
	 * 基于两个stack实现的后续遍历
	 * s1为辅助栈，s2为最终结果栈
	 *
	 * @param head
	 */
	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> s2 = new Stack<Node>();
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop();
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	/**
	 * 后续遍历
	 * 基于一个stack实现
	 *
	 * @param h
	 */
	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				//左子树没有处理完的情况
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				//右子树没有处理完的情况
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				//左右子树都处理完的情况
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
