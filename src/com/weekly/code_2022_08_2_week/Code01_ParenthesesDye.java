package com.weekly.code_2022_08_2_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/8/12-09:10
 * @desc 来自猿辅导
 * 给定一个数组arr，和一个正数k
 * 如果arr[i] == 0，表示i这里既可以是左括号也可以是右括号，
 * 而且可以涂上1~k每一种颜色
 * 如果arr[i] != 0，表示i这里已经确定是左括号，颜色就是arr[i]的值
 * 那么arr整体就可以变成某个括号字符串，并且每个括号字符都带有颜色
 * 返回在括号字符串合法的前提下，有多少种不同的染色方案
 * 不管是排列、还是颜色，括号字符串任何一点不一样，就算不同的染色方案
 * 最后的结果%10001，为了方便，我们不处理mod，就管核心思路
 * 2 <= arr长度 <= 5000
 * 1 <= k <= 1000
 * 0 <= arr[i] <= k
 */
public class Code01_ParenthesesDye {

    public static int ways1(int[] arr, int k) {
        if ((arr.length & 1) != 0) {
            return 0;
        }
        return func(arr, 0, k);
    }

    public static int func(int[] arr, int index, int k) {
        if (index == arr.length) {
            int n = arr.length;
            int[] stack = new int[n];
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > 0) {
                    stack[size++] = arr[i];
                } else {
                    if (size == 0 || stack[--size] != -arr[i]) {
                        return 0;
                    }
                }
            }
            return size == 0 ? 1 : 0;
        } else if (arr[index] != 0) {
            return func(arr, index + 1, k);
        } else {
            int ans = 0;
            for (int color = 1; color <= k; color++) {
                arr[index] = color;
                ans += func(arr, index + 1, k);
                arr[index] = -color;
                ans += func(arr, index + 1, k);
                arr[index] = 0;
            }
            return ans;
        }
    }

    /*
    ====================================================================================================================
     */

    // 求合法的括号组合数量，忽略染色
    // 假设已有b个确定的左括号，那么对应着一共有b个确定的右括号
    // 那么假设括号总数量n，剩下 n - 2b 个括号可以自由组合
    // 自由组合的括号一共可以分成 (n - 2b) / 2 组
    // 随意选一种染色方案都是可行的
    public static int ways2(int[] arr, int k) {
        int n = arr.length;
        if ((n & 1) != 0) {
            return -1;
        }
        int a = combines(arr);
        int b = 0;
        for (int num : arr) {
            if (num != 0) {
                b++;
            }
        }
        return a * ((int) Math.pow(k, (n - (b << 1)) >> 1));
    }

    // 求合法括号的组成数量
    private static int combines(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return process(arr, 0, 0, dp);
    }

    // arr[i...] 范围去做决定
    // arr[0 ... i - 1]已经做完决定，此时左括号的数量比右括号多 j 个
    // 返回最终的合法括号数量
    private static int process(int[] arr, int i, int j, int[][] dp) {
        if (i == arr.length) {
            return j == 0 ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        if (arr.length - i < j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (arr[i] != 0) { // 确定是左括号
            ans = process(arr, i + 1, j + 1, dp);
        } else {
            ans = process(arr, i + 1, j - 1, dp) +
                    process(arr, i + 1, j + 1, dp);
        }
        dp[i][j] = ans;
        return ans;
    }

    // for test
    public static int[] randomArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Math.random() < 0.5 ? 0 : ((int) (Math.random() * k) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 5;
        int K = 4;
        int testTimes = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = ((int) (Math.random() * N) + 1) << 1;
            int k = (int) (Math.random() * K) + 1;
            int[] arr = randomArray(n, k);
            int ans1 = ways1(arr, k);
            int ans2 = ways2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 5000;
        int k = 1000;
        System.out.println("数组长度 : " + n);
        System.out.println("颜色数量 : " + k);
        int[] arr = randomArray(n, k);
        long start = System.currentTimeMillis();
        ways2(arr, k);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + "毫秒");
        System.out.println("性能测试结束");
    }
}
