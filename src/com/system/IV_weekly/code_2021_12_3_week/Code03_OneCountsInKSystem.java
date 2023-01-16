package com.system.IV_weekly.code_2021_12_3_week;

/**
 * @author ycb
 * @date 2021/12/18-14:04
 * @description https://www.nowcoder.com/test/33701596/summary
 */
public class Code03_OneCountsInKSystem {

    // 二分的技巧
    public static long minM(int n, int k) {
        int len = bits(n, k);
        long l = 0;
        long r = power(k, len + 1);
        long ans = r;
        while (l <= r) {
            long m = l + ((r - l) >> 1);
            if (ones(m, k) >= n) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }

    // k进制表示下的n一共有几位
    public static int bits(long n, int k) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= k;
        }
        return len;
    }

    // 在k进制的前提下，一共有power位，要满足1的数量，此时的右边界在哪里
    public static long power(long base, int power) {
        long ans = 1;
        while (power != 0) {
            if ((power & 1) != 0) {
                ans *= base;
            }
            base *= base;
            power >>= 1;
        }
        return ans;
    }

    public static long ones(long num, int k) {
        int len = bits(num, k);
        if (len <= 1) {
            return len;
        }
        long offset = power(k, len - 1);
        long first = num / offset;
        long curOne = first == 1 ? (num % offset) + 1 : offset;
        long restOne = first * (len - 1) * (offset / k);
        return curOne + restOne + ones(num % offset, k);
    }

    public static void main(String[] args) {
        long base = 10;
        int power = 8;
        long ans = power(base, power);
        System.out.println(ans);
    }

}
