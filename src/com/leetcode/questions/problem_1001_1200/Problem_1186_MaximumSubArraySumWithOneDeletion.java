package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2023/6/27-14:27
 */
public class Problem_1186_MaximumSubArraySumWithOneDeletion {

    // dp0：以 arr[i] 位置元素结尾，删除 0 次的非空子数组的最大值
    // dp1：以 arr[i] 位置元素结尾，删除 1 次的非空子数组的最大值
    public static int maximumSum(int[] arr) {
        int dp0 = arr[0], dp1 = 0, ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            ans = Math.max(ans, Math.max(dp0, dp1));
        }
        return ans;
    }
}
