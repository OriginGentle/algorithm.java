package com.leetcode.problem_1201_1400;

import java.util.HashMap;

/**
 * @author ycb
 * @since 2021/11/5-14:50
 */
public class Problem_1218_LongestArithmeticSubsequenceOfGivenDifference {

    public static int longestSubsequence(int[] arr, int difference) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 0;
        // dp[i] 必须以arr[i]结尾形成的最长等差子序列的长度
        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int num : arr) {
            dp.put(num, dp.getOrDefault(num - difference, 0) + 1);
            ans = Math.max(ans, dp.get(num));
        }
        return ans;
    }
}
