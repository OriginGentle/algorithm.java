package com.system.IV_weekly.code_2022_06_2_week;

/**
 * @author ycb
 * @date 2022/6/19-13:33
 * @desc https://leetcode.cn/problems/sum-of-total-strength-of-wizards/
 */
public class Code02_SumOfTotalStrengthOfWizards {

    public static final int MOD = 1000000007;

    // 单调栈
    public static int totalStrength(int[] arr) {
        int n = arr.length;
        long preSum = arr[0];
        // 前缀和的前缀和
        // 数组：：：：：  2   4   7   9   1   4
        // 前缀和：：：：  2   6   13  21  22  26
        // 前缀和的前缀和：2   8   21  42  64  90
        long[] preSumOfSum = new long[n];
        preSumOfSum[0] = preSum;
        for (int i = 1; i < n; i++) {
            preSum += arr[i];
            preSumOfSum[i] = (preSumOfSum[i - 1] + preSum) % MOD;
        }
        int[] stack = new int[n];
        int size = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (size > 0 && arr[stack[size - 1]] >= arr[i]) {
                int m = stack[--size];
                int l = size > 0 ? stack[size - 1] : -1;
                // m ： 当前数，作为最小值
                // l ： < 当前数，离当前数最近，到不了
                // i ： <= 当前数，到不了
                ans += magicSum(arr, preSumOfSum, l, m, i);
                ans %= MOD;
            }
            stack[size++] = i;
        }
        while (size > 0) {
            int m = stack[--size];
            int l = size > 0 ? stack[size - 1] : -1;
            ans += magicSum(arr, preSumOfSum, l, m, n);
            ans %= MOD;
        }
        return (int) ans;
    }

    public static long magicSum(int[] arr, long[] preSumOfSum, int l, int m, int r) {
        long left = (m - l) * (
                preSumOfSum[r - 1]
                        - (m - 1 >= 0 ? preSumOfSum[m - 1] : 0)
                        + MOD
        ) % MOD;
        long right = (r - m) * (
                (m - 1 >= 0 ? preSumOfSum[m - 1] : 0)
                        - (l - 1 >= 0 ? preSumOfSum[l - 1] : 0)
                        + MOD
        ) % MOD;
        return arr[m] * ((left - right + MOD) % MOD);
    }
}
