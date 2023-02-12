package com.system.C_training.day15;

/**
 * @author ycb
 * @date 2021/9/11-12:40
 * @description https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class Code02_BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        // 枚举每一个抛物线的上升阶段
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

}
