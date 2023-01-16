package com.system.IV_weekly.code_2022_07_4_week;

/**
 * @author ycb
 * @date 2022/7/30-14:54
 * @desc https://leetcode.cn/problems/matchsticks-to-square/
 */
public class Code02_MatchsticksToSquare {

    public boolean makesquare(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int[] dp = new int[1 << arr.length];
        return process(arr, 0, 0, sum >> 2, 4, dp);
    }

    public static boolean process(int[] arr, int status, long cur, long len, int edges, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        if (edges == 0) {
            ans = status == (1 << arr.length) - 1;
        } else {
            for (int i = 0; i < arr.length && !ans; i++) {
                if (((1 << i) & status) == 0 && cur + arr[i] <= len) {
                    if (cur + arr[i] == len) {
                        ans = process(arr, (1 << i) | status, 0, len, edges - 1, dp);
                    } else {
                        ans = process(arr, (1 << i) | status, cur + arr[i], len, edges, dp);
                    }
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
