package com.weekly.code_2022_04_3_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/4/22
 * @desc 来自腾讯音乐
 * 原本数组中都是大于0、小于等于k的数字，是一个单调不减的数组
 * 其中可能有相等的数字，总体趋势是递增的
 * 但是其中有些位置的数被替换成了0，我们需要求出所有的把0替换的方案数量：
 * 1）填充的每一个数可以大于等于前一个数，小于等于后一个数
 * 2）填充的每一个数不能大于k
 */
public class Code03_ValidSortedArrayWays {

    public static long ways1(int[] arr, int k) {
        int n = arr.length;
        // dp[i][j] 一共i个格子，随意填，但是不能降序，共j种数可以选
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            // 只有一种数可以选
            dp[i][1] = 1;
        }
        for (int j = 1; j <= k; j++) {
            // 只有一个格子需要填，有j种数可以选
            dp[1][j] = j;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                // dp[i][j-1] : 当前选的不是最小值
                // dp[i-1][j] : 当前选的就是最小值
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        long ans = 1;
        for (int i = 0, j = 0; i < n; i++) {
            if (arr[i] == 0) {
                j = i + 1;
                while (j < n && arr[j] == 0) {
                    j++;
                }
                int leftVal = i - 1 >= 0 ? arr[i - 1] : 1;
                int rightVal = j < n ? arr[j] : k;

                ans *= dp[j - i][rightVal - leftVal + 1];
                i = j;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 数学方法
    // a ~ b 范围上的数随便选，可以重复，一共选m个
    // 选出有序序列的方案数：c(b - a + m, m)
    public static long ways2(int[] arr, int k) {
        long res = 1;
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j = i + 1;
                while (j < arr.length && arr[j] == 0) {
                    j++;
                }
                int leftVal = i - 1 >= 0 ? arr[i - 1] : 1;
                int rightVal = j < arr.length ? arr[j] : k;
                int numbers = j - i;
                res *= count(rightVal - leftVal + numbers, numbers);
                i = j;
            }
        }
        return res;
    }

    // 从 a 个数里面，选出 b 个数，一共有多种选法
    // 排列组合问题
    public static long count(int a, int b) {
        if (a == b) {
            return 1;
        }
        long x = 1;
        long y = 1;
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    // for test
    public static int[] randomArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * k) + 1;
        }
        Arrays.sort(ans);
        for (int i = 0; i < n; i++) {
            ans[i] = Math.random() < 0.5 ? 0 : ans[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int K = 30;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * K) + 1;
            int[] arr = randomArray(n, k);
            long ans1 = ways1(arr, k);
            long ans2 = ways2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");
    }
}
