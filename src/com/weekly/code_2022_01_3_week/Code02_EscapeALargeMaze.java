package com.weekly.code_2022_01_3_week;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ycb
 * @date 2022/1/23-16:34
 * @description https://leetcode.com/problems/escape-a-large-maze/
 */
public class Code02_EscapeALargeMaze {

    public static long offset = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        int maxPoints = n * (n - 1) / 2;
        HashSet<Long> blockSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            blockSet.add((long) blocked[i][0] * offset + blocked[i][1]);
        }
        return bfs(source[0], source[1], target[0], target[1], maxPoints, blockSet)
                && bfs(target[0], target[1], source[0], source[1], maxPoints, blockSet);
    }

    public static boolean bfs(int fromX, int fromY, int toX, int toY, int maxPoints, HashSet<Long> blockSet) {
        HashSet<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        visited.add((long) fromX * offset + fromY);
        queue.add((long) fromX * offset + fromY);
        while (!queue.isEmpty() && (visited.size() <= maxPoints)) {
            long cur = queue.poll();
            long curX = cur / offset;
            long curY = cur - curX * offset;
            if (findAndAdd(curX - 1, curY, toX, toY, blockSet, visited, queue)
                    || findAndAdd(curX + 1, curY, toX, toY, blockSet, visited, queue)
                    || findAndAdd(curX, curY - 1, toX, toY, blockSet, visited, queue)
                    || findAndAdd(curX, curY + 1, toX, toY, blockSet, visited, queue)) {
                return true;
            }
        }
        return visited.size() > maxPoints;
    }

    // 来到的点，(row, col)
    // 要寻找的目标点，toX， toY
    // HashSet<Long> blockSet存着不能走的格子！障碍点！
    // HashSet<Long> visited, Queue<Long> queue 为了宽度优先遍历服务的！
    // visited，已经处理过的点，请不要重复的放入queue
    // 如果已经到达了(toX， toY)
    public static boolean findAndAdd(long row, long col,
                                     int toX, int toY, HashSet<Long> blockSet,
                                     HashSet<Long> visited, Queue<Long> queue) {
        if (row < 0 || row == offset || col < 0 || col == offset) {
            return false;
        }
        if (row == toX && col == toY) {
            return true;
        }
        long value = row * offset + col;
        if (!blockSet.contains(value) && !visited.contains(value)) {
            visited.add(value);
            queue.add(value);
        }
        return false;
    }
}
