package com.weekly.code_2022_10_2_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/10/16-17:14
 * @desc 来自京东
 * 给定一个数组arr，长度为n
 * 相邻的两个数里面至少要有一个被选出来，组成子序列
 * 求选出来的数字构成的所有可能的子序列中，最大中位数是多少
 * 中位数的定义为上中位数
 * [1, 2, 3, 4]的上中位数是2
 * [1, 2, 3, 4, 5]的上中位数是3
 * 2 <=  n <= 10^5
 * 1 <= arr[i] <= 10^9
 */
public class Code02_BestMedianPickAdjacent {

    // 暴力方法
    public static int bestMedian1(int[] arr) {
        int[] path = new int[arr.length];
        return process(arr, 0, true, path, 0);
    }

    public static int process(int[] arr, int i, boolean pre, int[] path, int size) {
        if (i == arr.length) {
            if (size == 0) {
                return 0;
            }
            int[] sort = new int[size];
            for (int j = 0; j < size; j++) {
                sort[j] = path[j];
            }
            Arrays.sort(sort);
            return sort[(sort.length - 1) / 2];
        } else {
            path[size] = arr[i];
            int ans = process(arr, i + 1, true, path, size + 1);
            if (pre) {
                ans = Math.max(ans, process(arr, i + 1, false, path, size));
            }
            return ans;
        }
    }

    /*
    ====================================================================================================================
     */

    public static int bestMedian2(int[] arr) {
        int n = arr.length;
        int[] sort = new int[n];
        for (int i = 0; i < n; i++) {
            sort[i] = arr[i];
        }

        Arrays.sort(sort);
        int l = 0;
        int r = n - 1;
        int ans = -1;
        int[] help = new int[n];
        int[][] dp = new int[n + 1][2];
        while (l <= r) {
            int m = (l + r) >> 1;
            if (maxSum(arr, help, dp, sort[m], n) > 0) {
                ans = sort[m];
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // 如果中位数定成median，
    // 如果任意相邻的两数，至少选一个，来生成序列
    // 所有这样的序列中，
    // 到底有没有一个序列，其中>= median的数字，能达到一半以上
    public static int maxSum(int[] arr, int[] help, int[][] dp, int median, int n) {
        for (int i = 0; i < n; i++) {
            help[i] = arr[i] >= median ? 1 : -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = help[i] + dp[i + 1][1];
            dp[i][1] = Math.max(help[i] + dp[i + 1][1], dp[i + 1][0]);
        }

        return dp[0][1];
    }

    /*
    ====================================================================================================================
     */

    // 数组中的值只有1和-1，
    // 你可以从左往右选择数字组成子序列，
    // 但是要求任何两个相邻的数，至少要选1个
    // 请返回子序列的最大累加和
    // arr : 数组
    // i : 当前来到i位置
    // pre : 前一个数字(i-1位置)，当初选了没有
    // 如果pre == 0, 表示i-1位置的数字，当初没有选
    // 如果pre == 1, 表示i-1位置的数字，当初选了
    // 返回arr[i...]的子序列，最大累加和
    public static int maxSum(int[] arr, int i, int pre) {
        if (i == arr.length)
            return 0;

        // 可能性1：就是要选i位置的数
        int p1 = maxSum(arr, i + 1, 1) + arr[i];

        // 可能性2：就是不要i位置的数
        int p2 = -1;
        if (pre == 1)
            p2 = maxSum(arr, i + 1, 0);

        return Math.max(p1, p2);
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    public static void main(String[] args) {
//		int[] test = { 5, 3, 6, 2, 9, 7 };
//		int[] sort = { 2, 3, 5, 6, 7, 9 };
//		int len = test.length;
//		int[] help = new int[len];
//		int[][] dp = new int[len + 1][2];
//		System.out.println(maxSum(test, help, dp, 5, len));
//		System.out.println(maxSum(test, help, dp, 7, len));
//		System.out.println(maxSum(test, help, dp, 6, len));
        int N = 20;
        int V = 1000;
        int testTimes = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int ans1 = bestMedian1(arr);
            int ans2 = bestMedian2(arr);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 100000;
        int v = 50000000;
        System.out.println("数组长度 : " + n);
        System.out.println("数值范围 : " + v);
        int[] arr = randomArray(n, v);
        long start = System.currentTimeMillis();
        bestMedian2(arr);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + "毫秒");
        System.out.println("性能测试结束");
    }
}
