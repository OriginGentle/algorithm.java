package com.weekly.code_2022_04_1_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/4/9
 * @desc 来自阿里笔试
 * 牛牛今年上幼儿园了，老师叫他学习减法
 * 老师给了他5个数字，他每次操作可以选择其中的4个数字减1
 * 减一之后的数字不能小于0，因为幼儿园的牛牛还没有接触过负数
 * 现在牛牛想知道，自己最多可以进行多少次这样的操作
 * <p>
 * 扩展问题:leetcode 2141
 * leetcode测试链接 : https://leetcode.com/problems/maximum-running-time-of-n-computers/
 */
public class Code01_FourNumbersMinusOne {

    public static long maxRunTime(int n, int[] arr) {
        Arrays.sort(arr);
        int len = arr.length;
        long[] preSum = new long[len];
        preSum[0] = arr[0];
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        long ans = -1;
        long l = 0;
        long m = 0;
        // 平均下来最多供电多少分钟
        long r = preSum[len - 1] / n;
        while (l <= r) {
            m = (l + r) / 2;
            if (can(arr, preSum, m, n)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // arr:电池的状态
    // sums：电池前缀和数组
    // time：持续的时间
    // num：需要供电的房子
    // 返回能不能给num个房子持续供电time时间
    private static boolean can(int[] arr, long[] sums, long time, int num) {
        int l = 0;
        int m = 0;
        int r = arr.length - 1;
        int left = arr.length;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= time) {
                left = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        num -= arr.length - left;
        long rest = left == 0 ? 0 : sums[left - 1];
        return time * (long) num <= rest;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] arr = {3, 3, 3};
        long ans = maxRunTime(n, arr);
        System.out.println(ans);
    }
}
