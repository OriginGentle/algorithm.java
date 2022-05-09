package com.leetcode.problem_1001_1200;

/**
 * @author ycb
 * @since 2022/2/12-21:11
 */
public class Problem_1020_NumberOfEnclaves {

    public static int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int index = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        uf.union(index, index + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        uf.union(index, index + n);
                    }
                }
            }
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !uf.isOnEdge(i * n + j)) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    public static class UnionFind {
        private int[] parent;
        private boolean[] onEdge;
        private int[] rank;

        public UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            onEdge = new boolean[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * n + j;
                        parent[index] = index;
                        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                            onEdge[index] = true;
                        }
                    }
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                if (rank[fx] > rank[fy]) {
                    parent[fy] = fx;
                    onEdge[fx] |= onEdge[fy];
                } else if (rank[fx] < rank[fy]) {
                    parent[fx] = fy;
                    onEdge[fy] |= onEdge[fx];
                } else {
                    parent[fy] = fx;
                    onEdge[fx] |= onEdge[fy];
                    rank[fx]++;
                }
            }
        }

        public boolean isOnEdge(int i) {
            return onEdge[find(i)];
        }
    }
}
