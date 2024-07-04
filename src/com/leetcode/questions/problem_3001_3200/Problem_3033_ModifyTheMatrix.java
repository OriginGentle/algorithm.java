package com.leetcode.questions.problem_3001_3200;

/**
 * @author ycb
 * @date 2024/7/5-01:52
 */
public class Problem_3033_ModifyTheMatrix {

    public int[][] modifiedMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for (int j = 0; j < m; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, matrix[i][j]);
            }

            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = max;
                }
            }
        }
        return matrix;
    }
}
