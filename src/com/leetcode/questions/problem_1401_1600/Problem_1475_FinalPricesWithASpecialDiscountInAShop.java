package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/9/1-08:28
 * @desc
 */
public class Problem_1475_FinalPricesWithASpecialDiscountInAShop {

    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }
}
