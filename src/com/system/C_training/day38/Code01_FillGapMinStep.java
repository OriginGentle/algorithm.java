package com.system.C_training.day38;

/**
 * @author ycb
 * @date 2021/10/25-11:00
 * @description 来自字节
 * 给定两个数a和b
 * 第1轮，把1选择给a或者b
 * 第2轮，把2选择给a或者b
 * ...
 * 第i轮，把i选择给a或者b
 * 想让a和b的值一样大，请问至少需要多少轮？
 */
public class Code01_FillGapMinStep {

    // 暴力方法
    public static int minStep1(int a, int b) {
        if (a == b) {
            return 0;
        }
        int limit = 15;
        return process(a, b, 1, limit);
    }

    public static int process(int a, int b, int i, int n) {
        if (i > n) {
            return Integer.MAX_VALUE;
        }
        if (a + i == b || a == b + i) {
            return i;
        }
        return Math.min(process(a + i, b, i + 1, n), process(a, b + i, i + 1, n));
    }

    /*
    ====================================================================================================================
     */

    public static int minStep2(int a, int b) {
        if (a == b) {
            return 0;
        }
        int s = Math.abs(a - b);
        int num = 1, sum = 0;
        for (; !(sum >= s && (sum - s) % 2 == 0); num++) {
            sum += num;
        }
        return num - 1;
    }

    /*
    ====================================================================================================================
     */

    // 假设较小数位a,较大数为b
    // s为两数的差值:s = b - a
    // 题目要求每轮只能把一个整数,给较大数或者较小数
    // 假设在第i轮的时候，两数相等
    // 1...i轮的过程中，给较小数的总和是c,给较大数的总和是d,即 a + c = b + d
    // b - a = s  --> b = s + a
    // a + c = s + a + d  --> c - d = s --> b - a = c - d
    // 有题意可得,1...i为等差数列, 所以 c + d = (i * (i + 1)) / 2
    // 假设 (i * (i + 1)) / 2 = sum --> c + d = sum
    // c = (sum + s) / 2   d = (sum - s) / 2
    // 进一步分析： 给较大数的总和d >= 0
    // 根据d的求解公式 d为偶数
    // 要求轮数尽量少，所以相当于 d 尽量小，而且d >= 0,且必须是整数,还得是偶数
    public static int minStep3(int a, int b) {
        if (a == b) {
            return 0;
        }
        int s = Math.abs(a - b);
        int begin = best(s << 1);
        for (; (begin * (begin + 1) / 2 - s) % 2 != 0; ) {
            begin++;
        }
        return begin;
    }

    private static int best(int s2) {
        int L = 0;
        int R = 1;
        for (; R * (R + 1) < s2; ) {
            L = R;
            R *= 2;
        }
        int ans = 0;
        while (L <= R) {
            int M = (L + R) / 2;
            if (M * (M + 1) >= s2) {
                ans = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int N = (int) ((Math.random() + 1) * 10);
        int best = best(N);
        System.out.println("N:" + N + " best:" + best);
        System.out.println("功能测试开始");
        for (int a = 1; a < 100; a++) {
            for (int b = 1; b < 100; b++) {
                int ans1 = minStep1(a, b);
                int ans2 = minStep2(a, b);
                int ans3 = minStep3(a, b);
                if (ans1 != ans2 || ans1 != ans3) {
                    System.out.println("出错了！");
                    System.out.println(a + " , " + b);
                    break;
                }
            }
        }
        System.out.println("功能测试结束");

        int a = 19019;
        int b = 8439284;
        int ans2 = minStep1(a, b);
        int ans3 = minStep2(a, b);
        System.out.println(ans2);
        System.out.println(ans3);
    }
}
