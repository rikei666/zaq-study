package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

public class Problem_0053_MaximumSubarray {

	public static int maxSubArray(int[] nums) {
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			cur += nums[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

}
