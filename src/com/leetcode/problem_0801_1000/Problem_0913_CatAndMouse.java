package com.leetcode.problem_0801_1000;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2022/1/4-16:10
 */
public class Problem_0913_CatAndMouse {

    private static final int flat = 0;
    private static final int mouseWin = 1;
    private static final int catWin = 2;

    public static int catMouseGame(int[][] graph) {
        int n = graph.length;
        // 最多进行2*n轮
        // dp[i][j][k] = a : 当前进行到第k轮,猫的位置在i,老鼠的位置在j,这一轮的结果为a
        int[][][] dp = new int[n][n][n << 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(graph, n, dp, 2, 1, 0);
    }

    // 当前进行到第count轮，猫的位置是catPos，老鼠的位置是mousePos
    // 返回在不超过2*n轮的前提下，这场游戏的结果是什么
    public static int process(int[][] graph, int n, int[][][] dp, int catPos, int mousePos, int count) {
        if (count >= (n << 1)) {
            return flat;
        }
        if (dp[catPos][mousePos][count] != -1) {
            return dp[catPos][mousePos][count];
        }
        // 猫抓到老鼠了 --> 猫赢了
        if (catPos == mousePos) {
            return dp[catPos][mousePos][count] = catWin;
        }
        // 老鼠进洞了 --> 老鼠赢了
        if (mousePos == 0) {
            return dp[catPos][mousePos][count] = mouseWin;
        }
        // 当前轮为偶数的话，到老鼠走
        if (count % 2 == 0) {
            int ans = catWin;
            // 老鼠下一步可以去的点
            for (int next : graph[mousePos]) {
                int nextAns = process(graph, n, dp, catPos, next, count + 1);
                if (nextAns == mouseWin) {
                    return dp[catPos][mousePos][count] = mouseWin;
                }
                // 当前的选择是平局，记录当前的答案，后续老鼠还有机会赢
                if (nextAns == flat) {
                    ans = flat;
                }
            }
            return dp[catPos][mousePos][count] = ans;
        } else { // 当前轮为奇数，到猫走
            int ans = mouseWin;
            for (int next : graph[catPos]) {
                // 猫不能进洞
                if (next != 0) {
                    int nextAns = process(graph, n, dp, next, mousePos, count + 1);
                    if (nextAns == catWin) {
                        return dp[catPos][mousePos][count] = catWin;
                    }
                    // 当前的选择是平局
                    if (nextAns == flat) {
                        ans = flat;
                    }
                }
            }
            return dp[catPos][mousePos][count] = ans;
        }
    }
}
