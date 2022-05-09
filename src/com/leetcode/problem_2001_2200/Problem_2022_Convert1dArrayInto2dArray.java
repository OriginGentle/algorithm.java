package com.leetcode.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/1/1-19:22
 */
public class Problem_2022_Convert1dArrayInto2dArray {

    public static int[][] construct2DArray(int[] original, int m, int n) {
        if (original == null || original.length != m * n) {
            return new int[0][0];
        }
        int[][] ans = new int[m][n];
        for (int i = 0, index = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[index++];
            }
        }
        return ans;
    }
}
