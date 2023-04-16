package com.system.D_weekly.code_2023_04_2_week;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/4/15-12:54
 * @desc 来自Indeed、谷歌、亚马逊、领英、英伟达
 * 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上
 * 你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
 * 骑士的走法和中国象棋中的马相似，走 “日” 字：
 * 即先向左（或右）走 1 格，再向上（或下）走 2 格
 * 或先向左（或右）走 2 格，再向上（或下）走 1 格
 * 每次移动，他都可以像中国象棋中的马一样，选八个方向中的一个前进
 * 返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数
 * 本题确保答案是一定存在的。
 * 测试链接 : https://leetcode.cn/problems/minimum-knight-moves/
 */
public class Code04_MinimumKnightMoves {

    public static int minKnightMoves(int x, int y) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                Comparator.comparingInt(a -> (a[0] + a[1])));

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        heap.add(new int[]{0, distance(0, 0, x, y), 0, 0});
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int cost = cur[0], row = cur[2], col = cur[3];
            if (isPop(map, row, col)) {
                continue;
            }
            if (row == x && col == y) {
                ans = cost;
                break;
            }
            close(map, row, col);
            add(map, cost + 1, row + 2, col + 1, x, y, heap);
            add(map, cost + 1, row + 1, col + 2, x, y, heap);
            add(map, cost + 1, row - 1, col + 2, x, y, heap);
            add(map, cost + 1, row - 2, col + 1, x, y, heap);
            add(map, cost + 1, row - 2, col - 1, x, y, heap);
            add(map, cost + 1, row - 1, col - 2, x, y, heap);
            add(map, cost + 1, row + 1, col - 2, x, y, heap);
            add(map, cost + 1, row + 2, col - 1, x, y, heap);
        }
        return ans;
    }

    // 判断（r,c）点是否来过
    public static boolean isPop(HashMap<Integer, HashSet<Integer>> map, int r, int c) {
        return map.containsKey(r) && map.get(r).contains(c);
    }

    public static void close(HashMap<Integer, HashSet<Integer>> map, int r, int c) {
        if (!map.containsKey(r)) {
            map.put(r, new HashSet<>());
        }
        map.get(r).add(c);
    }

    public static void add(HashMap<Integer, HashSet<Integer>> map,
                           int r, int c, int x, int y, int cost,
                           PriorityQueue<int[]> heap) {
        if (!isPop(map, r, c)) {
            heap.add(new int[]{cost, distance(r, c, x, y), r, c});
        }
    }

    // 曼哈顿距离
    // 预估函数
    public static int distance(int r, int c, int x, int y) {
        return (Math.abs(r - x) + Math.abs(c - y)) / 3;
    }
}
