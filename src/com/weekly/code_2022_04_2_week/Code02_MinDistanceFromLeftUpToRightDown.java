package com.weekly.code_2022_04_2_week;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/4/17-15:00
 * @description 来自网易 3.27笔试
 * 一个二维矩阵，上面只有 0 和 1，只能上下左右移动
 * 如果移动前后的元素值相同，则耗费 1 ，否则耗费 2。
 * 问从左上到右下的最小耗费
 */
public class Code02_MinDistanceFromLeftUpToRightDown {

    // 一个错误的贪心
    public static int bestWalk1(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n][m];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + (map[0][i - 1] == map[0][i] ? 1 : 2);
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + (map[i - 1][0] == map[i][0] ? 1 : 2);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + (map[i - 1][j] == map[i][j] ? 1 : 2);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + (map[i][j - 1] == map[i][j] ? 1 : 2));
            }
        }
        return dp[n - 1][m - 1];
    }

    /*
    ====================================================================================================================
     */

    public static int bestWalk2(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        boolean[][] poped = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, 0, 0});
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int dis = cur[0];
            int row = cur[1];
            int col = cur[2];
            if (poped[row][col]) {
                continue;
            }
            poped[row][col] = true;
            if (row == n - 1 && col == m - 1) {
                ans = dis;
                break;
            }
            add(dis, row - 1, col, map[row][col], n, m, map, poped, heap);
            add(dis, row + 1, col, map[row][col], n, m, map, poped, heap);
            add(dis, row, col - 1, map[row][col], n, m, map, poped, heap);
            add(dis, row, col + 1, map[row][col], n, m, map, poped, heap);
        }
        return ans;
    }

    public static void add(int preDis, int row, int col, int preValue, int n, int m,
                           int[][] map, boolean[][] poped, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < n && col >= 0 && col < m && !poped[row][col]) {
            heap.add(new int[]{preDis + (map[row][col] == preValue ? 1 : 2), row, col});
        }
    }

    // for test
    public static int[][] randomMatrix(int n, int m) {
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = (int) (Math.random() * 2);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 100;
        int m = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[][] map = randomMatrix(n, m);
            int ans1 = bestWalk1(map);
            int ans2 = bestWalk2(map);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                for (int[] arr : map) {
                    for (int num : arr) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
