package com.zaqbest.study.foundation.alg.zcy.top_interview.topinterviewquestions;

/**
 * 水王问题
 *
 * 时间复杂度O(N)
 *
 * 参考
 * {@link com.zaqbest.study.foundation.alg.zcy.training.zdemo.term04.class03.Code05_FindKMajority}
 */
public class Problem_0169_MajorityElement {

	public static int majorityElement(int[] nums) {
		int cand = 0;
		int HP = 0;
		for (int i = 0; i < nums.length; i++) {
			if (HP == 0) {
				cand = nums[i];
				HP = 1;
			} else if (nums[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		return cand;
	}

}
