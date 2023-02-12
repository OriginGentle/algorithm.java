package com.system.D_weekly.code_2022_04_1_week;

/**
 * @author ycb
 * @date 2022/4/9
 * @desc 来自小红书
 * 小红书第一题：
 * 薯队长从北向南穿过一片红薯地（南北长M，东西宽N），红薯地被划分为1x1的方格，
 * 他可以从北边的任何一个格子出发，到达南边的任何一个格子，
 * 但每一步只能走到东南、正南、西南方向的三个格子之一，
 * 而且不能跨出红薯地，他可以获得经过的格子上的所有红薯，请问他可以获得最多的红薯个数。
 */
public class Code04_MaxScoreMoveInBoard {

    public static int maxScore1(int[][] map) {
        int ans = 0;
        for (int col = 0; col < map[0].length; col++) {
            ans = Math.max(ans, process1(map, 0, col));
        }
        return ans;
    }

    private static int process1(int[][] map, int row, int col) {
        if (col < 0 || col == map[0].length) {
            return 0;
        }
        if (row == map.length - 1) {
            return map[row][col];
        }
        int cur = map[row][col];
        int p1 = process1(map, row + 1, col - 1);
        int p2 = process1(map, row + 1, col);
        int p3 = process1(map, row + 1, col + 1);
        return cur + Math.max(p1, Math.max(p2, p3));
    }

    /*
    ====================================================================================================================
     */

    public static int maxScore2(int[][] map) {
        int ans = 0;
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        for (int col = 0; col < m; col++) {
            ans = Math.max(ans,process2(map, 0, col, dp));
        }
        return ans;
    }

    private static int process2(int[][] map, int row, int col, int[][] dp) {
        if (col < 0 || col == map[0].length) {
            return 0;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int ans = 0;
        if (row == map.length - 1) {
            ans = map[row][col];
        } else {
            int p1 = process2(map, row + 1, col - 1, dp);
            int p2 = process2(map, row + 1, col, dp);
            int p3 = process2(map, row + 1, col + 1, dp);
            ans = map[row][col] + Math.max(p1, Math.max(p2, p3));
        }
        dp[row][col] = ans;
        return ans;
    }

    // for test
    public static int[][] randomArray(int n, int m, int val) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = (int) (Math.random() * val);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 10;
        int val = 100;
        int times = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int[][] arr = randomArray(n, m, val);
            int ans1 = maxScore1(arr);
            int ans2 = maxScore2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
