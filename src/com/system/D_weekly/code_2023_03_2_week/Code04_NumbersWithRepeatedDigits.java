package com.system.D_weekly.code_2023_03_2_week;

/**
 * @author ycb
 * @date 2023/3/10-13:36
 * @desc 给定正整数 n
 * 返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
 * 测试链接 : https://leetcode.cn/problems/numbers-with-repeated-digits/
 */
public class Code04_NumbersWithRepeatedDigits {

    public static int numDupDigitsAtMostN(int n) {
        if (n <= 10) {
            return 0;
        }
        // n =      32435
        // offset = 10000
        // len    =  5
        int offset = 1, len = 1, tmp = n / 10;
        while (tmp > 0) {
            offset *= 10;
            len++;
            tmp /= 10;
        }

        int nonRepeat = 0;
        // 分别求 1 ~ 4 位数的不重复的数字数量
        for (int i = 1; i < len; i++) {
            nonRepeat += numAllLen(i);
        }

        if (len <= 10) {
            int status = 0b1111111111;
            // 此时 n 的最高位数字是 3
            // 先求固定长度为5位数的，最高位数字不如3的不重复的数字的数量
            nonRepeat += (n / offset - 1) * numRest(offset / 10, status ^ 1);
            // 最高位数字是 3 的情况
            nonRepeat += process(offset / 10, status ^ (1 << (n / offset)), n);
        }

        return n - nonRepeat + 1;
    }

    public static int process(int offset, int status, int n) {
        if (offset == 0) {
            return 1;
        }
        int ans = 0;
        int first = (n / offset) % 10;
        for (int i = 0; i < first; i++) {
            if ((status & (1 << i)) != 0) {
                ans += numRest(offset / 10, status ^ (1 << i));
            }
        }

        if ((status & (1 << first)) != 0) {
            ans += process(offset / 10, status ^ (1 << first), n);
        }
        return ans;
    }

    public static int numRest(int offset, int status) {
        int cnt = hammingWeight(status);
        int ans = 1;
        while (offset > 0) {
            ans *= cnt;
            cnt--;
            offset /= 10;
        }
        return ans;
    }

    public static int hammingWeight(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

    public static int numAllLen(int len) {
        if (len > 10) {
            return 0;
        }
        if (len == 1) {
            return 10;
        }
        int ans = 9, cur = 9;
        while (--len > 0) {
            ans *= cur;
            cur--;
        }
        return ans;
    }
}
