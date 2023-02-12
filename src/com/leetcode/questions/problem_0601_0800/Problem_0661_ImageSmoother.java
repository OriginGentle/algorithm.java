package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/3/24-8:33
 */
public class Problem_0661_ImageSmoother {

    public int[][] imageSmoother1(int[][] img) {
        int n = img.length, m = img[0].length;
        int[][] ans = new int[n][m];
        int[][] nexts = new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0, count = 0;
                for (int[] next : nexts) {
                    int nx = i + next[0], ny = j + next[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    sum += img[nx][ny];
                    count++;
                }
                ans[i][j] = sum / count;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public int[][] imageSmoother2(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] sum = new int[m + 10][n + 10];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + img[i - 1][j - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int a = Math.max(0, i - 1), b = Math.max(0, j - 1);
                int c = Math.min(m - 1, i + 1), d = Math.min(n - 1, j + 1);
                int cnt = (c - a + 1) * (d - b + 1);
                int tot = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
                ans[i][j] = tot / cnt;
            }
        }
        return ans;
    }
}
