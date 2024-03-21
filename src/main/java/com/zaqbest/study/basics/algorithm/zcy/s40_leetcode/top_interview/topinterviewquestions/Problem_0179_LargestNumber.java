package com.zaqbest.study.basics.algorithm.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non-negative integers nums,
 * arrange them such that they form the largest number and return it.
 *
 * Input: nums = [10,2]
 * Output: "210"
 */
public class Problem_0179_LargestNumber {

	public static class MyComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return (o2 + o1).compareTo(o1 + o2);
		}

	}

	public String largestNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(strs, new MyComparator());
		StringBuilder builder = new StringBuilder();
		for (String str : strs) {
			builder.append(str);
		}
		String ans = builder.toString();
		char[] str = ans.toCharArray();
		//去除前导的0
		int index = -1;
		for (int i = 0; i < str.length; i++) {
			if (str[i] != '0') {
				index = i;
				break;
			}
		}
		return index == -1 ? "0" : ans.substring(index);
	}

}
