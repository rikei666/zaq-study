package com.zaqbest.study.alg.zcy.training.study;

/**
 * 42. 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water
 *
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
public class Problem_0042_Trap {
    public static void main(String[] args) {
        int ans = trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        //int ans = trap(new int[]{4,2,0,3,2,5});
        System.out.println(ans);
    }
    public static int trap(int[] height) {
        int N = height.length;
        int[] leftMax = new int[N];//leftMax[i]指的是0...i的最大值
        leftMax[0] = height[0];
        for (int i = 1; i < N; i++){
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        int[] rightMax = new int[N];
        rightMax[N-1] = height[N-1];
        for (int i = N-2; i >= 0; i--){//rightMax[i]指的是i...N-1的最大值
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        //计算每一个的容积
        int ans = 0;
        for(int i = 0; i < N; i++){
            ans += Math.max(Math.min(leftMax[i], rightMax[i])-height[i], 0);
        }

        return ans;
    }
}
