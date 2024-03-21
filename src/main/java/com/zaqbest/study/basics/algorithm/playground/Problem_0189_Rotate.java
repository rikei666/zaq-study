package com.zaqbest.study.basics.algorithm.playground;

import cn.hutool.core.util.ArrayUtil;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150
 */
public class Problem_0189_Rotate {
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums,0, n-k-1);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n-1);
    }

    private static void reverse(int[] nums, int from, int to){
        while (from < to){
            int t = nums[from];
            nums[from] = nums[to];
            nums[to] = t;

            from++;
            to--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
//        reverse(arr, 2, 5);
        rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }
}
