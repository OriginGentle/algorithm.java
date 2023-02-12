package com.leetcode.questions.problem_1201_1400;

/**
 * @author ycb
 * @since 2022/2/5-12:42
 */
public class Problem_1219_PathWithMaximumGold {

    private int[] next = {1, 0, -1, 0, 1};
    private int[][] matrix;
    private int n, m, ans = 0;

    public int getMaximumGold(int[][] grid) {
        this.matrix = grid;
        this.n = matrix.length;
        this.m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    dfs(i, j, 0);
                }
            }
        }
        return ans;
    }

    private void dfs(int x, int y, int gold) {
        int cur = matrix[x][y];
        gold += cur;
        ans = Math.max(ans, gold);
        matrix[x][y] = 0;
        for (int i = 1; i < next.length; i++) {
            int nx = x + next[i - 1], ny = y + next[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] != 0) {
                dfs(nx, ny, gold);
            }
        }
        matrix[x][y] = cur;
    }
}
