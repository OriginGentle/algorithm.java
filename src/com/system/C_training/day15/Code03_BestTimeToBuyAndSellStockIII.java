package com.system.C_training.day15;

/**
 * @author ycb
 * @date 2021/9/11-12:40
 * @description https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class Code03_BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 特殊情况:0位置买，0位置卖，收益就为0
        int ans = 0;
        // arr[0...i]范围上做一次交易获得的收益，减去一个良好的买入
        int doneOnceMinusBuyMax = -prices[0];
        // 这一次交易，获得的最好收益
        int doneOnceMax = 0;
        // 记录i位置之前的最小值
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            // 在当前位置选择卖，获得最大收益是多少
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            // 在当前位置选择买
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
        }
        return ans;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] ans = new int[maxSize];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] a = {4, 7, 8, 7, 5, 2, 3, 6};
        maxProfit(a);

        int maxSize = 8;
        int maxValue = 10;
        int[] arr = generateRandomArray(maxSize, maxValue);
        printArr(arr);
        int ans = maxProfit(arr);
        System.out.println(ans);
    }

}
