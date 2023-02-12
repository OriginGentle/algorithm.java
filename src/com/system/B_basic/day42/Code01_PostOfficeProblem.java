package com.system.B_basic.day42;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/11/20-15:28
 * @description 一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量。
 * 选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
 * 【举例】
 * arr=[1,2,3,4,5,1000]，num=2。
 * 第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。那么 1 位置到邮局的距离 为 2， 2 位置到邮局距离为 1，
 * 3 位置到邮局的距离为 0，4 位置到邮局的距离为 1，5 位置到邮局的距 离为 2，1000 位置到邮局的距离为 0。
 * 这种方案下的总距离为 6， 其他任何方案的总距离都不会 比该方案的总距离更短，所以返回6
 */
public class Code01_PostOfficeProblem {

    public static int min1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        // 只有一个邮局的情况
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        // dp[i][j] : arr[0...i]上建j个邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离
        int[][] dp = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
        }
        // dp[0][j] : arr[0...0] 上建j个邮局，使所有的居民点到最近邮局的总距离最短
        // 很明显dp[0][j] = 0
        for (int i = 1; i < N; i++) {
            // num > i : 邮局的数量大于居民点个数
            // 最小距离为0
            for (int j = 2; j <= Math.min(i, num); j++) {
                int ans = Integer.MAX_VALUE;
                // [0.......k]由j-1个邮局搞定
                // [k + 1...i]由最后一个邮局搞定
                for (int k = 0; k <= i; k++) {
                    ans = Math.min(ans, dp[k][j - 1] + w[k + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][num];
    }

    /*
    ====================================================================================================================
     */

    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1;
        }
        // dp[0][j] : arr[0...0] 上建j个邮局，使所有的居民点到最近邮局的总距离最短
        // 很明显dp[0][j] = 0
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= 0; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        choose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = choose;
            }
        }
        return dp[N - 1][num];
    }

    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 50;
        int maxValue = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
