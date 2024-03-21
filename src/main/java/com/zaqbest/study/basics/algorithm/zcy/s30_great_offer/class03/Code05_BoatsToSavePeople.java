package com.zaqbest.study.basics.algorithm.zcy.s30_great_offer.class03;

import com.zaqbest.study.basics.algorithm.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * 返回最少的船数
 * 测试链接 : https://leetcode.cn/problems/boats-to-save-people/
 *
 * 思路
 * - 双指针向中间移动
 */

public class Code05_BoatsToSavePeople {

	/**
	 *
	 * @param arr
	 * @param limit
	 * @return
	 */
	public static int numRescueBoats1(int[] arr, int limit) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		Arrays.sort(arr);
		//如果有超过船重量的人，无法满足
		if (arr[N - 1] > limit) {
			return -1;
		}
		//找到<= limit/2的最右位置
		int lessR = -1;
		for (int i = N - 1; i >= 0; i--) {
			if (arr[i] <= (limit / 2)) {
				lessR = i;
				break;
			}
		}
		//如果每个人都大于limit/2, 每个人都要一条船
		if (lessR == -1) {
			return N;
		}
		int L = lessR;
		int R = lessR + 1;
		int noUsed = 0;
		while (L >= 0) {
			int solved = 0;
			while (R < N && arr[L] + arr[R] <= limit) {
				R++;
				solved++;
			}
			if (solved == 0) {
				noUsed++;
				L--;
			} else {
				L = Math.max(-1, L - solved);
			}
		}
		int all = lessR + 1;
		int used = all - noUsed;
		int moreUnsolved = (N - all) - used;
		return used + ((noUsed + 1) >> 1) + moreUnsolved;
	}

	// 首尾双指针的解法
	public static int numRescueBoats2(int[] people, int limit) {
		Arrays.sort(people);
		int ans = 0;
		int l = 0;
		int r = people.length - 1;
		int sum = 0;
		while (l <= r) {
			sum = l == r ? people[l] : people[l] + people[r];
			if (sum > limit) {
				r--;
			} else {
				l++;
				r--;
			}
			ans++;
		}
		return ans;
	}

	public static void main(String[] args) {

		System.out.println("test begin!");
		for (int i =0; i < 100000; i++){
			int[] people = ArrayUtil.generateRandomArray(1000, 10);
			int limit = 10;

			int r1 = numRescueBoats1(people, limit);
			int r2 = numRescueBoats2(people, limit);
			if (r1 != r2){
				System.out.println("oops!");
				break;
			}
		}
		System.out.println("test end!");

	}
}
