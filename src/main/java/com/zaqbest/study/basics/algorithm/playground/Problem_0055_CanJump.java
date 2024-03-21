package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0055_CanJump {
    public static boolean canJump(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if (i > max){
                return false;
            }
            //推高max
            max = Math.max(i + nums[i], max);

            //已经超过数组最大下标
            if (max >= nums.length - 1){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int nums1[] = {2,3,1,1,4};
        System.out.println(canJump(nums1));
        int nums2[] = {3,2,1,0,4};
        System.out.println(canJump(nums2));
        int nums3[] = {0};
        System.out.println(canJump(nums3));


    }
}
