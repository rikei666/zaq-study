package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

public class Problem_0136_SingleNumber {

	public static int singleNumber(int[] nums) {
		int eor = 0;
		for (int num : nums) {
			eor ^= num;
		}
		return eor;
	}

}
