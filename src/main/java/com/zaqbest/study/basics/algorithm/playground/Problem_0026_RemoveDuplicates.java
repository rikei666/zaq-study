package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0026_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1){
            return 1;
        }

        int i = 0, j = 1;

        while (j <= nums.length - 1){
            if (nums[i] == nums[j]){
                j++;
            } else {
                nums[i+1] = nums[j];
                i++;
                j++;
            }
        }

        return i+1;
    }

}
