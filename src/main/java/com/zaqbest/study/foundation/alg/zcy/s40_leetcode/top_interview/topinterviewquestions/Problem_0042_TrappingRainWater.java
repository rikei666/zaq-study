package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 42 接雨水问题
 *
 * {@link com.zaqbest.study.foundation.alg.zcy.s11_trainingcamp.study.Problem_0042_Trap}
 */
public class Problem_0042_TrappingRainWater {

	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
