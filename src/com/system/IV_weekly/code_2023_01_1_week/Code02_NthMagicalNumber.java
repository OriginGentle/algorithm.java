package com.system.IV_weekly.code_2023_01_1_week;

/**
 * @author ycb
 * @date 2023/1/6-09:01
 * @desc 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。
 * 因为答案可能很大，所以返回答案 对 10^9 + 7 取模 后的值。
 * 测试链接 : https://leetcode.cn/problems/nth-magical-number/
 */
public class Code02_NthMagicalNumber {

    public static int nthMagicalNumber(int n, int a, int b) {
        long lcm = (long) a / gcd(a, b) * b;
        long ans = 0;
        for (long l = 0, r = (long) n * Math.min(a, b), m = 0; l <= r; ) {
            m = (l + r) / 2;
            if (m / a + m / b - m / lcm >= n) {
                r = m - 1;
                ans = m;
            } else {
                l = m + 1;
            }
        }
        return (int) (ans % 1000000007);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
