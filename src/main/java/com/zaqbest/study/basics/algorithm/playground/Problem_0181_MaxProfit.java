package com.zaqbest.study.basics.algorithm.playground;

public class Problem_0181_MaxProfit {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int ans = 0;

        for (int i = 1; i < prices.length;i++){
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i]-min);
        }

        return ans;
    }

}
