package com.leetcode.questions.problem_1601_1800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/12/10-20:43
 */
public class Problem_1691_MaximumHeightByStackingCuboids {

    public int maxHeight(int[][] cuboids) {
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }

        Arrays.sort(cuboids, (a, b) -> a[0] != b[0] ?
                a[0] - b[0] : (a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]));

        int n = cuboids.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 排序后，cuboids[j][0] <= cuboids[i][0]恒成立
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    // cuboids[j] 可以堆在cuboids[i]上面
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }

            dp[i] += cuboids[i][2];
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
