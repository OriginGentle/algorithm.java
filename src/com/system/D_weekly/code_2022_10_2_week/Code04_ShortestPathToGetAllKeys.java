package com.system.D_weekly.code_2022_10_2_week;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/10/16-17:15
 * @desc https://leetcode.cn/problems/shortest-path-to-get-all-keys
 * 给定一个二维网格grid，其中：
 * // '.' 代表一个空房间
 * // '#' 代表一堵
 * // '@'是起点
 * // 小写字母代表钥匙
 * // 大写字母代表锁
 * // 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间
 * // 我们不能在网格外面行走，也无法穿过一堵墙
 * // 如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * // 假设 k为 钥匙/锁 的个数，且满足1 <= k <= 6，
 * // 字母表中的前 k个字母在网格中都有自己对应的一个小写和一个大写字母
 * // 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁
 * // 另外，代表钥匙和锁的字母互为大小写并按字母顺序排列
 * // 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回-1。
 */
public class Code04_ShortestPathToGetAllKeys {

    public static int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = grid[i].toCharArray();
        }

        int m = map[0].length;
        return dijkstra(map, n, m);
    }

    private static int dijkstra(char[][] map, int n, int m) {
        int startX = 0, startY = 0, keys = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '@') {
                    startX = i;
                    startY = j;
                }

                if (map[i][j] >= 'a' && map[i][j] <= 'z')
                    keys++;
            }
        }

        int limit = (1 << keys) - 1;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        boolean[][][] visited = new boolean[n][m][1 << keys];
        heap.add(new int[]{startX, startY, 0, 0});

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0], y = cur[1], s = cur[2], w = cur[3];

            if (s == limit)
                return w;

            if (visited[x][y][s])
                continue;

            visited[x][y][s] = true;

            add(x - 1, y, s, w, n, m, map, visited, heap);
            add(x + 1, y, s, w, n, m, map, visited, heap);
            add(x, y - 1, s, w, n, m, map, visited, heap);
            add(x, y + 1, s, w, n, m, map, visited, heap);
        }
        return -1;
    }

    public static void add(int x, int y,
                           int s, int w,
                           int n, int m,
                           char[][] map,
                           boolean[][][] visited, PriorityQueue<int[]> heap) {

        if (x < 0 || x == n || y < 0 || y == m || map[x][y] == '#')
            return;

        // 当前位置是锁
        if (map[x][y] >= 'A' && map[x][y] <= 'Z') {
            if (!visited[x][y][s] && (s & (1 << (map[x][y] - 'A'))) != 0) {
                heap.add(new int[]{x, y, s, w + 1});
            }
        } else {
            if (map[x][y] >= 'a' && map[x][y] <= 'z') {
                s |= 1 << (map[x][y] - 'a');
            }
            if (!visited[x][y][s]) {
                heap.add(new int[]{x, y, s, w + 1});
            }
        }
    }
}
