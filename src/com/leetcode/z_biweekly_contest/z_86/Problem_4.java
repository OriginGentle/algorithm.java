package com.leetcode.z_biweekly_contest.z_86;

/**
 * @author ycb
 * @date 2022/9/3-22:14
 */
public class Problem_4 {

    // c[i]表示第 i 个机器人充电时间
    // run[i]表示第 i 个机器人运行时间
    // k个机器人，总花销计算公式如下：
    // max(chargeTimes) + k * sum(runningCosts)
    public static int maximumRobots(int[] c, int[] run, long budget) {
        int n = c.length;

        RangeMaxQuery rmp = new RangeMaxQuery(c);

        long[] preSum = new long[n];
        preSum[0] = run[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + run[i];
        }

        long ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            long max = rmp.max(l + 1, r + 1);
            long sum = preSum[r] - (l == 0 ? 0 : preSum[l - 1]);
            long cost = max + sum * (r - l + 1);
            if (budget < cost) {
                l++;
            } else {
                ans = Math.max(ans, r - l + 1);
            }
        }
        return (int) ans;
    }

    public static class RangeMaxQuery {
        private int[][] max;

        // 下标从1开始，约定俗成
        public RangeMaxQuery(int[] arr) {
            int n = arr.length;
            int k = power2(n);
            max = new int[n + 1][k + 1];
            for (int i = 1; i <= n; i++) {
                max[i][0] = arr[i - 1];
            }
            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public int max(int l, int r) {
            int k = power2(r - l + 1);
            return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
        }

        public int power2(int num) {
            int ans = 0;
            while ((1 << ans) <= (num >> 1)) {
                ans++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] c = {11, 12, 19};
        int[] run = {10, 8, 7};
        long b = 19;
        int ans = maximumRobots(c, run, b);
        System.out.println(ans);
    }
}
