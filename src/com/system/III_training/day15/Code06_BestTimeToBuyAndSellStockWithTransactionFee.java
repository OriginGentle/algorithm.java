package com.system.III_training.day15;

/**
 * @author ycb
 * @date 2021/9/11-12:41
 * @description https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {

    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int N = prices.length;
        int bestBuy = -prices[0] - fee;
        int bestSell = 0;
        for (int i = 1; i < prices.length; i++) {
            // 来到i位置了，必须买
            int curBuy = bestSell - prices[i] - fee;
            // 如果在i必须卖  整体最优（收入 - 良好批发价 - fee）
            int curSell = bestBuy + prices[i];
            bestBuy = Math.max(curBuy, bestBuy);
            bestSell = Math.max(curSell, bestSell);
        }
        return bestSell;
    }
}
