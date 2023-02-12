package com.system.C_training.day15;

/**
 * @author ycb
 * @date 2021/9/11-12:39
 * @description https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class Code01_BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 必须在0时刻卖掉
        int ans = 0;
        int min = prices[0];
        // 枚举每一个时刻卖掉得到的最大利润
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

}
