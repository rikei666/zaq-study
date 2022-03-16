package com.zaqbest.study.alg.zcy.top_interview.topinterviewquestions;

/**
 * 11.盛水最多的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @see
 * {@link com.zaqbest.study.alg.zcy.training.study.Problem_0042_Trap}
 */
public class Problem_0011_ContainerWithMostWater {

	public static int maxArea(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}

}
