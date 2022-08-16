package com.leetcode.problem_biweekly;

public class Problem_4 {

    public static int[] offset = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    // n >= 1
    public static int countSpecialNumbers(int n) {
        // n 一共由几位数字组成
        // n : 697645617
        // len : 9位
        int lenOfNum = getLenOfNum(n);
        if (lenOfNum == 1) return n;

        int ans = 0;
        // 1 - 8
        // 依次计算位数为 1-8 的情况下
        // 有多少个数字符合要求
        for (int i = 1; i < lenOfNum; i++) {
            ans += all(i);
        }

        // 最高位的数字
        int firstNum = n / offset[lenOfNum];
        // 最高位是9
        // 那么此时尝试最高位是 1-8的情况
        ans += (firstNum - 1) * small(lenOfNum - 1, 9);
        ans += process(n, lenOfNum, lenOfNum - 1, 1 << firstNum);
        return ans;
    }

    // 用位信息来保存数字的使用状态
    private static int process(int num, int len, int rest, int status) {
        if (rest == 0) {
            return 1;
        }
        int cur = (num / offset[rest]) % 10;
        int cnt = 0;
        for (int i = 0; i < cur; i++) {
            if ((status & (1 << i)) == 0) {
                cnt++;
            }
        }
        int ans = cnt * small(rest - 1, 10 - len + rest - 1);
        if ((status & (1 << cur)) == 0) {
            ans += process(num, len, rest - 1, status | (1 << cur));
        }
        return ans;
    }

    public static int small(int bits, int cand) {
        int ans = 1;
        for (int i = 0; i < bits; i++, cand--) {
            ans *= cand;
        }
        return ans;
    }

    public static int all(int bits) {
        int ans = 9;
        int cur = 9;
        while (--bits > 0) {
            ans *= cur--;
        }
        return ans;
    }

    public static int getLenOfNum(long n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }

    public static void main(String[] args) {
        int n = 135;
        int ans = countSpecialNumbers(n);
        System.out.println(ans);
    }
}
