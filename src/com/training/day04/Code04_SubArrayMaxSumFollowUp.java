package com.training.day04;

/**
 * @Author ycb
 * @Date 2021/8/1-17:28
 * @Description 返回一个数组中，选择的数字不能相邻的情况下，
 * 最大子序列累加和
 */
public class Code04_SubArrayMaxSumFollowUp {

    /**
     * 定义dp[i] : 表示arr[0...i]范围上，在不能取相邻数的情况下，返回所有组合中的最大累加和
     * 在arr[0...i]范围上，在不能取相邻数的情况下，得到的最大累加和，可能性分类：
     * 可能性 1): 选出的组合，不包含arr[i]。那么dp[i] = dp[i-1]
     * 可能性 2): 选出的组合，只包含arr[i]。那么dp[i] = arr[i]
     * 可能性 3): 选出的组合，包含arr[i], 且包含arr[0...i-2]范围上的累加和。那么dp[i] = arr[i] + dp[i-2]
     * 综上所述：dp[i] = Max { dp[i-1], arr[i] , arr[i] + dp[i-2] }
     *
     * @param arr
     * @return
     */
    public static int subSqeMaxSumNoNext(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[0]);
        // dp[i] : arr[0...i]上挑选，满足不相邻的情况的下，随意挑选，最大的累加和
        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
        }
        return dp[arr.length - 1];
    }
}
