package com.training.day23;

/**
 * @author ycb
 * @date 2021/10/8-8:20
 * @description https://leetcode.com/problems/minimum-cost-to-merge-stones/
 */
public class Code05_MinimumCostToMergeStones {

    public static int mergeStones1(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) { // 判断最终有k堆进行合成
            return -1;
        }
        int[] preSum = new int[n + 1];
        // 坐标从1开始
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        return process1(0, n - 1, 1, stones, K, preSum);
    }

    // P >= 1
    // arr[L..R] 一定要弄出part份，返回最低代价
    // arr、K、preSum（前缀累加和数组，求i..j的累加和，就是O(1)了）
    public static int process1(int L, int R, int P, int[] arr, int K, int[] preSum) {
        if (L == R) {
            return P == 1 ? 0 : -1;
        }
        // L...R 不止一个数
        if (P == 1) {
            int next = process1(L, R, K, arr, K, preSum);
            return next == -1 ? -1 : next + preSum[R + 1] - preSum[L];
        } else { // P > 1
            int ans = Integer.MAX_VALUE;
            // L...mid是第1块，剩下的是part-1块
            for (int mid = L; mid < R; mid += K - 1) {
                int next1 = process1(L, mid, 1, arr, K, preSum);
                int next2 = process1(mid + 1, R, P - 1, arr, K, preSum);
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                }
            }
            return ans;
        }
    }

    /*
    ====================================================================================================================
     */

    public static int mergeStones2(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }
        int[] preSum = new int[n + 1];
        // 位置从1开始
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        int[][][] dp = new int[n][n][K + 1];
        return process2(0, n - 1, 1, stones, K, preSum, dp);
    }

    public static int process2(int L, int R, int P, int[] arr, int K, int[] preSum, int[][][] dp) {
        if (dp[L][R][P] != 0) {
            return dp[L][R][P];
        }
        if (L == R) {
            return P == 1 ? 0 : -1;
        }
        if (P == 1) {
            int next = process2(L, R, K, arr, K, preSum, dp);
            if (next == -1) {
                dp[L][R][P] = -1;
                return -1;
            } else {
                dp[L][R][P] = next + preSum[R + 1] - preSum[L];
                return next + preSum[R + 1] - preSum[L];
            }
        } else {
            int ans = Integer.MAX_VALUE;
            // i...mid是第1块，剩下的是part-1块
            for (int mid = L; mid < R; mid += K - 1) {
                int next1 = process2(L, mid, 1, arr, K, preSum, dp);
                int next2 = process2(mid + 1, R, P - 1, arr, K, preSum, dp);
                if (next1 == -1 || next2 == -1) {
                    dp[L][R][P] = -1;
                    return -1;
                } else {
                    ans = Math.min(ans, next1 + next2);
                }
            }
            dp[L][R][P] = ans;
            return ans;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSize = 12;
        int maxValue = 100;
        System.out.println("Test begin");
        for (int testTime = 0; testTime < 100000; testTime++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int K = (int) (Math.random() * 7) + 2;
            int ans1 = mergeStones1(arr, K);
            int ans2 = mergeStones2(arr, K);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
    }
}
