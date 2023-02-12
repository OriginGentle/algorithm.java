package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @since 2022/3/23-15:13
 */
public class problem_0556_ReshapeTheMatrix {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n = mat.length, m = mat[0].length;
        if (n * m != r * c) {
            return mat;
        }
        int[][] ans = new int[r][c];
        for (int i = 0; i < n * m; i++) {
            ans[i / c][i % c] = mat[i / m][i % m];
        }
        return ans;
    }
}
