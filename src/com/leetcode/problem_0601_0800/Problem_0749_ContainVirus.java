package com.leetcode.problem_0601_0800;

import java.util.*;

/**
 * @author ycb
 * @date 2022/7/21-16:48
 */
public class Problem_0749_ContainVirus {

    public int[][] g;
    public int n, m;
    public int ans;
    public boolean[][] visited;
    public final int[] next = {1, 0, -1, 0, 1};

    public int containVirus(int[][] isInfected) {
        g = isInfected;
        n = g.length;
        m = g[0].length;
        while (true) {
            int cnt = getCnt();
            if (cnt == 0)
                break;
            ans += cnt;
        }
        return ans;
    }

    private int getCnt() {
        visited = new boolean[n][m];
        int ans = 0, max = 0;
        // 每个连通块的点集
        List<Set<Integer>> l1 = new ArrayList<>();
        // 每个连通块的感染候选点集
        List<Set<Integer>> l2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1 && !visited[i][j]) {
                    // 当前连通块的点集
                    Set<Integer> s1 = new HashSet<>();
                    // 当前连通块的感染候选点集
                    Set<Integer> s2 = new HashSet<>();
                    int b = search(i, j, s1, s2);
                    int a = s2.size();
                    if (a > max) {
                        max = a;
                        ans = b;
                    }
                    l1.add(s1);
                    l2.add(s2);
                }
            }
        }

        for (int i = 0; i < l2.size(); i++) {
            for (int loc : l2.get(i).size() == max ? l1.get(i) : l2.get(i)) {
                int x = loc / m;
                int y = loc % m;
                g[x][y] = l2.get(i).size() == max ? -1 : 1;
            }
        }
        return ans;
    }

    private int search(int x, int y, Set<Integer> s1, Set<Integer> s2) {
        int ans = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        visited[x][y] = true;
        deque.addLast(new int[]{x, y});
        s1.add(x * m + y);
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int r = cur[0], c = cur[1];
            for (int i = 1; i < next.length; i++) {
                int nr = r + next[i - 1], nc = c + next[i], loc = nr * m + nc;
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc])
                    continue;
                if (g[nr][nc] == 1) { // 感染点
                    s1.add(loc);
                    visited[nr][nc] = true;
                    deque.addLast(new int[]{nr, nc});
                } else if (g[nr][nc] == 0) { // 感染候选点
                    s2.add(loc);
                    ans++;
                }
            }
        }
        return ans;
    }
}
