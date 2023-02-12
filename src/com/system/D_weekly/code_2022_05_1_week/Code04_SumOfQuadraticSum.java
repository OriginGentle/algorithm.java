package com.system.D_weekly.code_2022_05_1_week;

/**
 * @author ycb
 * @date 2022/5/8-15:01
 * @desc 蓝桥杯练习题
 * f(i) : i的所有因子，每个因子都平方之后，累加起来
 * 比如f(10) = 1平方 + 2平方 + 5平方 + 10平方 = 1 + 4 + 25 + 100 = 130
 * 给定一个数n，求f(1) + f(2) + .. + f(n)
 * n <= 10的9次方
 */
public class Code04_SumOfQuadraticSum {

    public static long sum1(long n) {
        int[] cnt = new int[(int) n + 1];
        for (int num = 1; num <= n; num++) {
            for (int j = 1; j <= num; j++) {
                if (num % j == 0) {
                    cnt[j]++;
                }
            }
        }
        long ans = 0;
        for (long i = 1; i <= n; i++) {
            ans += i * i * (long) cnt[(int) i];
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 时间复杂度O(开平方根N + 开平方根N * logN)
    public static long sum2(long n) {
        long sqrt = (long) Math.pow((double) n, 0.5);
        long ans = 0;
        for (long i = 1; i <= sqrt; i++) {
            ans += i * i * (n / i);
        }
        // 后半段
        // 给定个数，求出有多少个因子，处在这个个数上
        // 二分
        for (long k = n / (sqrt + 1); k >= 1; k--) {
            ans += sumOfLimitNumber(n, k);
        }
        return ans;
    }

    // 平方和公式：n(n + 1)(2n + 1) / 6
    public static long sumOfLimitNumber(long val, long n) {
        long r = cover(val, n);
        long l = cover(val, n + 1);
        return ((r * (r + 1) * ((r << 1) + 1) - l * (l + 1) * ((l << 1) + 1)) * n) / 6;
    }

    public static long cover(long n, long k) {
        long l = 1;
        long r = n;
        long m = 0;
        long ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (m * k <= n) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static void test(int n) {
        int[] cnt = new int[n + 1];
        for (int num = 1; num <= n; num++) {
            for (int j = 1; j <= num; j++) {
                if (num % j == 0) {
                    cnt[j]++;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("因子 : " + i + ", 个数 : " + cnt[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        for (long i = 1; i < 1000; i++) {
            if (sum1(i) != sum2(i)) {
                System.out.println("出错了!");
                System.out.println(sum1(i));
                System.out.println(sum2(i));
                break;
            }
        }
        System.out.println("测试结束");

        long n = 50000000000L; // 5 * 10的10次方
        long start = System.currentTimeMillis();
        sum2(n);
        long end = System.currentTimeMillis();
        System.out.println("大样本测试，n = " + n);
        System.out.println("运行时间 : " + (end - start) + " ms");
    }
}
