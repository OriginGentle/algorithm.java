package com.leetcode.problem_0801_1000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/9/18-21:10
 */
public class Problem_0827_MakingALargeIsland {

    public static final int N = 510;

    public static int[] fa = new int[N * N];

    public static int[] size = new int[N * N];

    public static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int find(int x) {
        if (fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }

    private static void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (size[ra] > size[rb]) {
            union(b, a);
        } else {
            size[rb] += size[ra];
            fa[ra] = fa[rb];
        }
    }

    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        for (int i = 1; i <= n * n; i++) {
            fa[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    for (int[] next : dir) {
                        int nx = i + next[0], ny = j + next[1];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1)
                            union(i * n + j + 1, nx * n + ny + 1);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1)
                    ans = Math.max(ans, size[find(i * n + j + 1)]);

                else {
                    int cnt = 1;
                    Set<Integer> set = new HashSet<>();

                    for (int[] next : dir) {
                        int nx = i + next[0], ny = j + next[1];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                            int root = find(nx * n + ny + 1);

                            if (set.contains(root))
                                continue;

                            cnt += size[root];
                            set.add(root);
                        }
                    }
                    ans = Math.max(cnt, ans);
                }
            }
        }
        return ans;
    }
}
