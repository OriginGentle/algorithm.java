package com.system.D_weekly.code_2023_05_4_week;

/**
 * @author ycb
 * @date 2023/5/26-13:26
 * @desc 来自华为
 * 一个数字n，一定要分成k份
 * 得到的乘积尽量大是多少
 * 数字n和k，可能非常大，到达10^12规模
 * 结果可能更大，所以返回结果对1000000007取模
 */
public class Code02_SplitNtoKMaxProduct {

    public static int maxValue1(int n, int k) {
        if (k < -1 || n < k) {
            return -1;
        }

        return process(n, k);
    }

    private static int process(int rest, int k) {
        if (k == 1) {
            return rest;
        }

        int ans = Integer.MIN_VALUE;
        for (int cur = 1; cur <= rest && (rest - cur) >= (k - 1); cur++) {
            int curAns = cur * process(rest - cur, k - 1);
            ans = Math.max(curAns, ans);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int maxValue2(int n, int k) {
        if (k == 0 || n < k) {
            return -1;
        }
        // 数字 n，一定要分 k 份
        // 每份可以先得到多少 n / k
        int a = n / k;
        // 有多少份可以升级为 a + 1
        int b = n % k;
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a + 1;
        }
        for (int i = 0; i < k - b; i++) {
            ans *= a;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int maxValue3(int n, int k) {
        if (n == 0 || n < k) {
            return -1;
        }

        int mod = 1000000007;
        long a = n / k;
        long b = n % k;
        long part1 = power(a + 1, b, mod);
        long part2 = power(a, k - b, mod);
        return (int) (part1 * part2) % mod;
    }

    // 快速幂
    private static long power(long a, long n, int mod) {
        long ans = 1;
        long tmp = a;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = (ans * tmp) % mod;
            }
            n >>= 1;
            tmp = (tmp * tmp) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 23;
        int k = 4;
        System.out.println(maxValue1(n, k));
        System.out.println(maxValue2(n, k));
        System.out.println(maxValue3(n, k));
    }
}
