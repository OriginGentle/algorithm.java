package com.leetcode;

/**
 * @author ycb
 * @since 2021/12/16-8:21
 */
public class Problem_0063_UniquePathsII {

    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] dp = new int[m];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }

    /*
    ====================================================================================================================
     */

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        return process(obstacleGrid, 0, 0, visited, n, m);
    }

    public static int process(int[][] arr, int i, int j, boolean[][] visited, int n, int m) {
        if (i == n - 1 && j == m - 1) {
            return arr[n - 1][m - 1] == 1 ? 0 : 1;
        }
        int ans = 0;
        // 在最后一行,只能往右走
        if (i == n - 1 && arr[i][j + 1] == 0 && !visited[i][j + 1]) {
            visited[i][j + 1] = true;
            ans += process(arr, i, j + 1, visited, n, m);
            visited[i][j + 1] = false;
        }
        // 在最后一列，只能往下走
        if (j == m - 1 && arr[i + 1][j] == 0 && !visited[i + 1][j]) {
            visited[i + 1][j] = true;
            ans += process(arr, i + 1, j, visited, n, m);
            visited[i + 1][j] = false;
        }
        if (i < n - 1 && j < m - 1) {
            if (arr[i + 1][j] == 0 && !visited[i + 1][j]) {
                visited[i + 1][j] = true;
                ans += process(arr, i + 1, j, visited, n, m);
                visited[i + 1][j] = false;
            }
            if (arr[i][j + 1] == 0 && !visited[i][j + 1]) {
                visited[i][j + 1] = true;
                ans += process(arr, i, j + 1, visited, n, m);
                visited[i][j + 1] = false;
            }
        }
        return ans;
    }

}
