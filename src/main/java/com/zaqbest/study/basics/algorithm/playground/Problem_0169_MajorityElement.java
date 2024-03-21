package com.zaqbest.study.basics.algorithm.playground;

/**
 * https://leetcode.cn/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
 */
public class Problem_0169_MajorityElement {
    public static int majorityElement(int[] nums) {
        int c = nums[0];
        int n=1;

        for (int i = 1; i < nums.length; i++){
            if (nums[i] == c){
                n++;
            } else {
                n--;
                if (n == 0){
                    c = nums[i];
                    n = 1;
                }
            }
        }

        return c;
    }

    public static void main(String[] args) {
        int nums[] = {3,2,3};
        int ans = majorityElement(nums);
        System.out.println(ans);
    }
}
