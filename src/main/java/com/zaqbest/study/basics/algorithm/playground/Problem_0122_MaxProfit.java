package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0122_MaxProfit {
    public int maxProfit(int[] prices) {
        int ans = 0;

        for (int i = 1; i < prices.length;i++){
            ans += Math.max(prices[i]-prices[i-1], 0);
        }

        return ans;
    }

}
