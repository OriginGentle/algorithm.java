package com.leetcode.questions.problem_1601_1800;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @since 2022/1/29-20:10
 */
public class Problem_1765_MapOfHighestPeak {

    // 宽度优先遍历技巧
    public static int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        int[][] ans = new int[n][m];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {
                    deque.addLast(new int[]{i, j});
                }
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[] next = {0, 1, 0, -1, 0};
        while (!deque.isEmpty()) {
            int[] info = deque.pollFirst();
            int x = info[0], y = info[1];
            for (int i = 1; i < 5; i++) {
                int nx = x + next[i - 1], ny = y + next[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (ans[nx][ny] != -1) {
                    continue;
                }
                ans[nx][ny] = ans[x][y] + 1;
                deque.addLast(new int[]{nx, ny});
            }
        }
        return ans;
    }
}
