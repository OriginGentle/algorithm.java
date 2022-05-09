package com.leetcode.problem_1001_1200;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author ycb
 * @since 2022/1/11-11:09
 */
public class Problem_1036_EscapeALargeMaze {

    // 在包围圈内
    public static final int BLOCKED = -1;
    // 不在包围圈内
    public static final int NOT_BLOCKED = 0;
    // 可以到达target
    public static final int FOUND = 1;

    public static int[] next = {0, -1, 0, 1, 0};

    public static final int BOUNDARY = 1000000;


    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }
        Set<String> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add(b[0] + "-" + b[1]);
        }
        int ans = bfs(blocked, source, target, set);
        if (ans == BLOCKED) { // 在包围圈内
            return false;
        } else if (ans == FOUND) { // 能到达目标位置
            return true;
        } else { // 不在包围圈内
            ans = bfs(blocked, target, source, set);
            return ans != BLOCKED;
        }
    }

    public static int bfs(int[][] blocked, int[] start, int[] end, Set<String> allBlocked) {
        int sx = start[0], sy = start[1];
        int ex = end[0], ey = end[1];
        int blockedCount = blocked.length * (blocked.length - 1) / 2;
        Pair startPair = new Pair(sx, sy);
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(startPair);
        Set<String> visited = new HashSet<>();
        visited.add(sx + "-" + sy);
        while (!queue.isEmpty() && blockedCount > 0) {
            Pair pair = queue.poll();
            int x = pair.x, y = pair.y;
            for (int i = 1; i <= 4; i++) {
                int nx = x + next[i - 1], ny = y + next[i];
                Pair nPair = new Pair(nx, ny);
                if (nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY && !allBlocked.contains(nx + "-" + ny)
                        && !visited.contains(nx + "-" + ny)) {
                    if (nx == ex && ny == ey) {
                        return FOUND;
                    }
                    --blockedCount;
                    queue.offer(nPair);
                    visited.add(nx + "-" + ny);
                }
            }
        }
        if (blockedCount > 0) {
            return BLOCKED;
        }
        return NOT_BLOCKED;
    }

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
