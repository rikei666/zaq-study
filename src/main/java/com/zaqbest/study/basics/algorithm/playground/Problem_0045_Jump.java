package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0045_Jump {
    public static int jump(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int step = 0;
        int cur = 0;
        int next = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (cur < i){
                step++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }

        return step;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(jump(nums));
        nums = new int[]{0};
        System.out.println(jump(nums));
    }
}
