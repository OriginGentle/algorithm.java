package com.leetcode.problem_1001_1200;

import java.util.*;

/**
 * @author ycb
 * @date 2023/2/2-08:37
 */
public class Problem_1129_ShortestPathWithAlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] nexts = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                nexts[i][j] = new ArrayList<>();
            }
        }

        for (int[] red : redEdges) {
            nexts[0][red[0]].add(red[1]);
        }

        for (int[] blue : blueEdges) {
            nexts[1][blue[0]].add(blue[1]);
        }

        int[][] dis = new int[2][n];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        dis[0][0] = 0;
        dis[1][0] = 0;
        queue.add(new int[]{0, 0});
        queue.add(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], t = cur[1];
            for (int y : nexts[1 - t][x]) {
                if (dis[1 - t][y] != Integer.MAX_VALUE) {
                    continue;
                }
                dis[1 - t][y] = dis[t][x] + 1;
                queue.add(new int[]{y, 1 - t});
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Math.min(dis[0][i], dis[1][i]);
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }
        return ans;
    }
}
