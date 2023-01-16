package com.system.IV_weekly.code_2022_11_1_week;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2022/11/4-10:28
 * @desc 来自蚂蚁金服
 * 小红有n个朋友, 她准备开个宴会，邀请一些朋友
 * i号朋友的愉悦值为a[i]，财富值为b[i]
 * 如果两个朋友同时参加宴会，这两个朋友之间的隔阂是其财富值差值的绝对值
 * 宴会的隔阂值，是财富差距最大的两人产生的财富值差值的绝对值
 * 宴会的愉悦值，是所有参加宴会朋友的愉悦值总和
 * 小红可以邀请任何人，
 * 希望宴会的愉悦值不能小于k的情况下， 宴会的隔阂值能最小是多少
 * 如果做不到，返回-1
 * 1 <= n <= 2 * 10^5
 * 1 <= 愉悦值、财富值 <= 10^9
 * 1 <= k <= 10^14
 */
public class Code03_HappyLimitLessGap {

    // 暴力方法
    public static int lessGap1(int[] a, int[] b, int k) {
        long ans = process(a, b, 0, k, Integer.MAX_VALUE, Integer.MIN_VALUE);
        return ans < Integer.MAX_VALUE ? (int) ans : -1;
    }

    public static long process(int[] a, int[] b, int i, int rest, int min, int max) {
        if (rest <= 0) {
            return (long) max - (long) min;
        }
        if (i == a.length) {
            return (long) Integer.MAX_VALUE;
        }
        long p1 = process(a, b, i + 1, rest, min, max);
        long p2 = process(a, b, i + 1, rest - a[i], Math.min(min, b[i]), Math.max(max, b[i]));
        return Math.min(p1, p2);
    }

    /*
    ====================================================================================================================
     */

    public static int lessGap2(int[] a, int[] b, int k) {
        int n = a.length;
        int[][] f = new int[n][2];
        int min = b[0], max = b[0];
        for (int i = 0; i < n; i++) {
            f[i][0] = a[i];
            f[i][1] = b[i];
            min = Math.min(min, b[i]);
            max = Math.max(max, b[i]);
        }

        Arrays.sort(f, Comparator.comparingInt(x -> x[1]));
        int l = 0, r = max - min, m;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (maxHappy(f, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static long maxHappy(int[][] f, int limit) {
        int n = f.length;
        long ans = 0, sum = 0;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && f[r][1] - f[l][1] <= limit) {
                sum += f[r++][0];
            }
            ans = Math.max(ans, sum);
            sum -= f[l][0];
            r = Math.max(r, l + 1);
        }
        return ans;
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int V = 20;
        int testTime = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] a = randomArray(n, V);
            int[] b = randomArray(n, V);
            int k = (int) (Math.random() * n * V) + 1;
            int ans1 = lessGap1(a, b, k);
            int ans2 = lessGap2(a, b, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
