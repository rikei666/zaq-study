package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0027_RemoveElement {
    public static int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0)
            return 0;

        int dest = nums.length - 1;
        int i = 0;

        while (i <= dest) {
            while (i <= dest && nums[i] == val){
                nums[i] = nums[dest--];
            }
            i++;
        }

        return dest+1;
    }

    public static void main(String[] args) {
        removeElement(new int[]{1}, 1);
    }
}
