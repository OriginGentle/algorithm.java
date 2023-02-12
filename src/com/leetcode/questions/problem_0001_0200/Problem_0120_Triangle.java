package com.leetcode.questions.problem_0001_0200;

import java.util.List;

/**
 * @author ycb
 * @date 2022/4/26
 */
public class Problem_0120_Triangle {

    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size() - 1;
        return process(triangle, n, 0, 0, 0);
    }

    private static int process(List<List<Integer>> triangle, int size, int level, int index, int sum) {
        if (level == size) {
            return sum + triangle.get(size).get(index);
        }
        int p1 = process(triangle, size, level + 1, index, sum + triangle.get(level).get(index));
        int p2 = process(triangle, size, level + 1, index + 1, sum + triangle.get(level).get(index));
        return Math.min(p1, p2);
    }

    /*
    ====================================================================================================================
     */

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int ans = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, dp[n - 1][i]);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }
}
