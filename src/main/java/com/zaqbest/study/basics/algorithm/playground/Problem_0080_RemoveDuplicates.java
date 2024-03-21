package com.zaqbest.study.basics.algorithm.playground;

/**
 * @see <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150">link</a>
 */
public class Problem_0080_RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2){
            return nums.length;
        }

        int i = 1, j = 2;

        while (j <= nums.length - 1){
            // 1,不相等
            // 2,相等，但是没有前一个值
            // 3，相等，但是前一个值不相等
            if (nums[i] != nums[j]
                    || (nums[i] == nums[j] && i == 0)
            ||(nums[i] == nums[j] && nums[i-1] != nums[j])){
                //赋值
                nums[i+1] = nums[j];
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i+1;
    }
}

