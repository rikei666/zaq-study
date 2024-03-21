package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0045_Jump {
    public static int jump(int[] nums) {
        //当前步数
        int curStep  = 0;
        //再多走一步，能达到的最大位置
        int nextMax = -1;

        for (int i = 0; i < nums.length; i++) {
            //必须要多走一步，才能到达当前位置
            if (i > nextMax) {
                curStep += 1;
            }

            //多走一步之后，能达到的最大位置
            nextMax = Math.max(nextMax, i + nums[i]);

            //发现多走1步后，已经可以到达结尾，返回结果
            if (nextMax >= nums.length - 1){
                return curStep + 1;
            }
        }
        return curStep +1;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        System.out.println(jump(nums));
    }
}
