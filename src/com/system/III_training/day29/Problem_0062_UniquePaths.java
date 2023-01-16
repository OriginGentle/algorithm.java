package com.system.III_training.day29;

/**
 * @author ycb
 * @since 2021/10/16-12:05
 */
public class Problem_0062_UniquePaths {

    public static int uniquePaths(int m, int n) {
        int right = n - 1;
        int all = m + n - 2;
        long o1 = 1, o2 = 1;
        // o1乘进去的个数 一定等于 o2乘进去的个数
        for (int i = right + 1, j = 1; i <= all; i++, j++) {
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1, o2);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int) o1;
    }

    // 调用的时候，保证初次调用时，m和n都不为0
    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
