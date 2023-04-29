package com.system.D_weekly.code_2023_04_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/4/29-00:36
 * @desc 你有一个凸的 n 边形，其每个顶点都有一个整数值。给定一个整数数组 values ，
 * 其中 values[i] 是第 i 个顶点的值（即 顺时针顺序 ）。
 * 假设将多边形 剖分 为 n - 2 个三角形。
 * 对于每个三角形，该三角形的值是顶点标记的乘积，
 * 三角剖分的分数是进行三角剖分后所有 n - 2 个三角形的值之和。
 * 返回 多边形进行三角剖分后可以得到的最低分 。
 * 测试链接 : https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/
 */
public class Code01_MinScoreTriangulationPolygon {

    public static int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(values, 0, n - 1, dp);
    }

    private static int process(int[] values, int i, int j, int[][] dp) {
        if (i >= j - 1) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            ans = Math.min(ans,
                    process(values, i, k, dp) +
                            process(values, k, j, dp) +
                            values[i] * values[k] * values[j]);
        }
        dp[i][j] = ans;
        return ans;
    }
}
