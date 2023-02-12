package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/11/22-23:33
 */
public class Problem_0878_NthMagicalNumber {

    public static final long MOD = (long) 1e9 + 7;

    public int nthMagicalNumber(int n, int a, int b) {
        long lcm = (long) a / gcd(a, b) * b;
        long L = 0, R = (long) Math.max(a, b) * n;
        while (L + 1 < R) {
            long M = (L + R) / 2;
            if (M / a + M / b - M / lcm >= n) {
                R = M;
            } else {
                L = M;
            }
        }

        return (int) (R % MOD);
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
