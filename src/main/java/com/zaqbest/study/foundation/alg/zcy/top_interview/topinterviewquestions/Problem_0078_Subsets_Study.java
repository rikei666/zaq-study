package com.zaqbest.study.foundation.alg.zcy.top_interview.topinterviewquestions;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class Problem_0078_Subsets_Study {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans  = new ArrayList<>();
		ans.add(new ArrayList<>());

		for (int i = 0; i < nums.length; i++){
			List<List<Integer>> tmp = new ArrayList<>();
			tmp.addAll(ans);

			for (List<Integer> item: ans){
				List<Integer> newItem = new ArrayList<>(item);
				newItem.add(nums[i]);
				tmp.add(newItem);
			}

			ans = tmp;
		}

		return ans;
	}

	public static void main(String[] args) {
		List<List<Integer>> ans = subsets(new int[]{1,2,3,4});
		System.out.printf(JSONUtil.toJsonStr(ans));
	}
}
