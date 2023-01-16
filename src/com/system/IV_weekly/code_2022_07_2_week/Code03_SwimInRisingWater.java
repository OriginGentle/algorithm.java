package com.system.IV_weekly.code_2022_07_2_week;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/7/17-13:49
 * @desc https://leetcode.cn/problems/swim-in-rising-water/
 */
public class Code03_SwimInRisingWater {

    // 并查集技巧
    public static int swimInWater1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] points = new int[n * m][3];
        int pi = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                points[pi][0] = i;
                points[pi][1] = j;
                points[pi++][2] = grid[i][j];
            }
        }
        Arrays.sort(points, (a, b) -> a[2] - b[2]);
        UnionFind uf = new UnionFind(n, m);
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            int r = points[i][0];
            int c = points[i][1];
            int v = points[i][2];
            if (r > 0 && grid[r - 1][c] <= v)
                uf.union(r, c, r - 1, c);

            if (r < n - 1 && grid[r + 1][c] <= v)
                uf.union(r, c, r + 1, c);

            if (c > 0 && grid[r][c - 1] <= v)
                uf.union(r, c, r, c - 1);

            if (c < m - 1 && grid[r][c + 1] <= v)
                uf.union(r, c, r, c + 1);

            if (uf.isSameSet(0, 0, n - 1, m - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }

    public static class UnionFind {
        public int col;
        public int pointSize;
        public int[] father;
        public int[] size;
        public int[] help;

        public UnionFind(int n, int m) {
            col = m;
            pointSize = n * m;
            father = new int[pointSize];
            size = new int[pointSize];
            help = new int[pointSize];
            for (int i = 0; i < pointSize; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            while (hi != 0) {
                father[help[--hi]] = i;
            }
            return i;
        }

        public int index(int i, int j) {
            return i * col + j;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int f1 = find(index(r1, c1));
            int f2 = find(index(r2, c2));
            if (f1 != f2) {
                // 小挂大
                if (size[f1] >= size[f2]) {
                    father[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    father[f1] = f2;
                    size[f2] += size[f1];
                }
            }
        }

        public boolean isSameSet(int r1, int c1, int r2, int c2) {
            return find(index(r1, c1)) == find(index(r2, c2));
        }
    }

    /*
    ====================================================================================================================
     */

    // Dijkstra算法
    public static int swimInWater2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][m];
        heap.add(new int[]{0, 0, grid[0][0]});
        int ans = 0;
        while (!heap.isEmpty()) {
            int r = heap.peek()[0];
            int c = heap.peek()[1];
            int v = heap.peek()[2];
            heap.poll();
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            if (r == n - 1 && c == m - 1) {
                ans = v;
                break;
            }
            add(grid, heap, visited, r - 1, c, v);
            add(grid, heap, visited, r + 1, c, v);
            add(grid, heap, visited, r, c - 1, v);
            add(grid, heap, visited, r, c + 1, v);
        }
        return ans;
    }

    public static void add(int[][] grid, PriorityQueue<int[]> heap, boolean[][] visited, int r, int c, int preV) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !visited[r][c]) {
            heap.add(new int[]{r, c, preV + Math.max(0, grid[r][c] - preV)});
        }
    }
}
