package com.system.II_basic.day46;

/**
 * @author ycb
 * @date 2021/11/23-8:20
 * @description https://leetcode.com/problems/remove-boxes/
 */
public class Code02_RemoveBoxes {

    public static int removeBoxes1(int[] boxes) {
        return process1(boxes, 0, boxes.length - 1, 0);
    }

    // arr[L...R]消除，而且前面跟着K个arr[L]这个数
    // 返回：所有东西都消掉，最大得分
    public static int process1(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        int ans = process1(arr, L + 1, R, 0) + (K + 1) * (K + 1);
        // 前面的K个X，和arr[L]数，合在一起了，现在有K+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[L] == arr[i]) {
                ans = Math.max(ans, process1(arr, L + 1, i - 1, 0) + process1(arr, i, R, K + 1));
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int removeBoxes2(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process2(boxes, 0, N - 1, 0, dp);
    }

    public static int process2(int[] arr, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int ans = process2(arr, L + 1, R, 0, dp) + (K + 1) * (K + 1);
        // 前面的K个X，和arr[L]数，合在一起了，现在有K+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[L] == arr[i]) {
                ans = Math.max(ans, process2(arr, L + 1, i - 1, 0, dp) + process2(arr, i, R, K + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int removeBoxes3(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process3(boxes, 0, N - 1, 0, dp);
    }

    public static int process3(int[] arr, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        // 找到开头，
        // 1,1,1,1,1,5
        // 3 4 5 6 7 8
        //
        int last = L;
        while (last + 1 <= R && arr[last + 1] == arr[L]) {
            last++;
        }
        // K个1     (K + last - L) last
        int pre = K + last - L;
        int ans = process3(arr, last + 1, R, 0, dp) + (pre + 1) * (pre + 1);
        for (int i = last + 2; i <= R; i++) {
            if (arr[L] == arr[i] && arr[i - 1] != arr[L]) {
                ans = Math.max(ans, process3(arr, last + 1, i - 1, 0, dp) + process3(arr, i, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }
}
