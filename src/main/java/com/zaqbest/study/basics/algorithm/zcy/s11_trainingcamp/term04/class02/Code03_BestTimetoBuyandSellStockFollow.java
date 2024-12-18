package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term04.class02;

/**
 * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格
 * 作为一个事后诸葛亮，你想知 道如果交易次数不超过K次，
 * 且每次交易只买卖一股，返回能挣到的最大钱数
 *
 * 思路：考虑最后一个位置i是不是最后一次卖出，枚举买入的位置0..i-1
 */
public class Code03_BestTimetoBuyandSellStockFollow {

	public static int dp(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[N][K + 1];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			int t = dp[0][j - 1] - prices[0];
			for (int i = 1; i < N; i++) {
				t = Math.max(t, dp[i][j - 1] - prices[i]);
				dp[i][j] = Math.max(dp[i - 1][j], t + prices[i]);
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		// dp一维表，做了空间压缩
		int[] dp = new int[N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			int pre = dp[0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[index];
				dp[index] = Math.max(dp[index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] test = { 4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146 };
		int K = 3;
		System.out.println(dp(K, test));
		System.out.println(maxProfit(K, test));

	}

}
