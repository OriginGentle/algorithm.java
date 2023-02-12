package com.system.C_training.day15;

/**
 * @author ycb
 * @date 2021/9/11-12:40
 * @description https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class Code04_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0 || K < 1) {
            return 0;
        }
        int N = prices.length;
        // K >= N / 2 --> 股票问题2
        if (K >= (N >> 1)) {
            return allTrans(prices);
        }
        // dp[i][j] : 在prices[0...i]位置上做不超过j次交易或得的最大收益
        int[][] dp = new int[N][K + 1];
        // dp[0][j] = 0
        // dp[i][0] = 0
        for (int j = 1; j <= K; j++) {
            // 完全不考虑1位置
            int p1 = dp[0][j];
            // 考虑1位置，则1位置为卖出时刻
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                int newP = dp[i][j - 1] - prices[i];
                best = Math.max(best, newP);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }
        return dp[N - 1][K];
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

}
