package com.weekly.code_2022_12_4_week;

/**
 * @author ycb
 * @date 2022/12/31-21:02
 * @desc 你现在手里有一份大小为 n x n 的 网格 grid
 * 上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
 * 并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
 * (x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 * 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/
 */
public class Code04_AsFarFromLandAsPossible {

    public static int[][] queue = new int[10000][2];
    public static int l, r, find;
    public static boolean[][] visited = new boolean[100][100];

    public static int maxDistance(int[][] grid) {
        l = 0;
        r = 0;
        find = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }

        int seats = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                } else {
                    seats++;
                }
            }
        }

        int distance = 0;
        while (l < r && find < seats) {
            int size = r - l;
            for (int i = 0; i < size && find < seats; i++, l++) {
                int row = queue[l][0], col = queue[l][1];
                add(row - 1, col, n, m, grid);
                add(row + 1, col, n, m, grid);
                add(row, col - 1, n, m, grid);
                add(row, col + 1, n, m, grid);
            }
            distance++;
        }
        return find == 0 ? -1 : distance;
    }

    public static void add(int i, int j, int n, int m, int[][] grid) {
        if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 0 && !visited[i][j]) {
            find++;
            visited[i][j] = true;
            queue[r][0] = i;
            queue[r++][1] = j;
        }
    }
}
