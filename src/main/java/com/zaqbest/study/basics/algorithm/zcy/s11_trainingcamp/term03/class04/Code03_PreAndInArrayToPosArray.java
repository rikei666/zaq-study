package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term03.class04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 */
public class Code03_PreAndInArrayToPosArray {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static int[] preInToPos1(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		int N = pre.length;
		int[] pos = new int[N];
		process1(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1);
		return pos;
	}

	public static void process1(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3) {
		if (L1 > R1) {
			return;
		}
		if (L1 == R1) {
			pos[L3] = pre[L1];
			return;
		}
		pos[R3] = pre[L1];
		int mid = L2;
		for (; mid <= R2; mid++) {
			if (in[mid] == pre[L1]) {
				break;
			}
		}
		int leftSize = mid - L2;
		process1(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1);
		process1(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1);
	}

	public static int[] preInToPos2(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		int N = pre.length;
		HashMap<Integer, Integer> inMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			inMap.put(in[i], i);
		}
		int[] pos = new int[N];
		process2(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, inMap);
		return pos;
	}

	public static void process2(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
			HashMap<Integer, Integer> inMap) {
		if (L1 > R1) {
			return;
		}
		if (L1 == R1) {
			pos[L3] = pre[L1];
			return;
		}
		pos[R3] = pre[L1];
		int mid = inMap.get(pre[L1]);
		int leftSize = mid - L2;
		process2(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);
		process2(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
	}

	// for test
	public static int[] getPreArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillPreArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillPreArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		arr.add(head.value);
		fillPreArray(head.left, arr);
		fillPreArray(head.right, arr);
	}

	// for test
	public static int[] getInArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillInArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillInArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		fillInArray(head.left, arr);
		arr.add(head.value);
		fillInArray(head.right, arr);
	}

	// for test
	public static int[] getPosArray(Node head) {
		ArrayList<Integer> arr = new ArrayList<>();
		fillPostArray(head, arr);
		int[] ans = new int[arr.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = arr.get(i);
		}
		return ans;
	}

	// for test
	public static void fillPostArray(Node head, ArrayList<Integer> arr) {
		if (head == null) {
			return;
		}
		fillPostArray(head.left, arr);
		fillPostArray(head.right, arr);
		arr.add(head.value);
	}

	// for test
	public static Node generateRandomTree(int value, int N) {
		HashSet<Integer> hasValue = new HashSet<Integer>();
		return createTree(value, 1, N, hasValue);
	}

	// for test
	public static Node createTree(int value, int level, int N, HashSet<Integer> hasValue) {
		if (level > N) {
			return null;
		}
		int cur = 0;
		do {
			cur = (int) (Math.random() * value) + 1;
		} while (hasValue.contains(cur));
		hasValue.add(cur);
		Node head = new Node(cur);
		head.left = createTree(value, level + 1, N, hasValue);
		head.right = createTree(value, level + 1, N, hasValue);
		return head;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 5;
		int value = 1000;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			Node head = generateRandomTree(value, N);
			int[] pre = getPreArray(head);
			int[] in = getInArray(head);
			int[] pos = getPosArray(head);
			int[] ans1 = preInToPos1(pre, in);
			int[] ans2 = preInToPos2(pre, in);
			if (!isEqual(pos, ans1) || !isEqual(ans1, ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");

	}
}
