package com.system.D_weekly.code_2022_12_2_week;

/**
 * @author ycb
 * @date 2022/12/17-00:35
 * @desc 来自亚马逊、谷歌、微软、Facebook、Bloomberg
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 * 测试链接 : https://leetcode.cn/problems/making-a-large-island/
 */
public class Code02_MakingALargeIsland {

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int id = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    infect(grid, i, j, id++, n, m);
            }
        }

        int[] size = new int[id];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 1) {
                    ans = Math.max(ans, ++size[grid[i][j]]);
                }
            }
        }

        boolean[] visited = new boolean[id];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int up = i - 1 >= 0 ? grid[i - 1][j] : 0;
                    int down = i + 1 < n ? grid[i + 1][j] : 0;
                    int left = j - 1 >= 0 ? grid[i][j - 1] : 0;
                    int right = j + 1 < m ? grid[i][j + 1] : 0;

                    int merge = 1 + size[up];
                    visited[up] = true;
                    if (!visited[down]) {
                        merge += size[down];
                        visited[down] = true;
                    }
                    if (!visited[left]) {
                        merge += size[left];
                        visited[left] = true;
                    }
                    if (!visited[right]) {
                        merge += size[right];
                        visited[right] = true;
                    }
                    ans = Math.max(ans, merge);
                    visited[up] = false;
                    visited[down] = false;
                    visited[left] = false;
                    visited[right] = false;
                }
            }
        }
        return ans;
    }

    public static void infect(int[][] arr, int i, int j, int val, int n, int m) {
        if (i < 0 || j < 0 || i == n || j == m || arr[i][j] != 1)
            return;

        arr[i][j] = val;
        infect(arr, i - 1, j, val, n, m);
        infect(arr, i + 1, j, val, n, m);
        infect(arr, i, j - 1, val, n, m);
        infect(arr, i, j + 1, val, n, m);
    }
}
