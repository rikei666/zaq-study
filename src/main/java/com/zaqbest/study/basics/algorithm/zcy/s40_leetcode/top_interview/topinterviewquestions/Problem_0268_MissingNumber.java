package com.zaqbest.study.basics.algorithm.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 查找缺少的第一个正整数
 *
 * 思路
 * - 双指针移动，l,r  无用的数字，都移动到垃圾区
 *
 * 分为3种情况：
 * 1, [l] < l ;
 * 2, [l] >= r
 * 3, [l] == [[l]]
 */
public class Problem_0268_MissingNumber {

	public static int missingNumber(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {
			if (arr[l] == l) {
				l++;
			} else if (arr[l] < l || arr[l] >= r || arr[arr[l]] == arr[l]) {
				swap(arr, l, --r);
			} else {
				swap(arr, l, arr[l]);
			}
		}
		return l;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
