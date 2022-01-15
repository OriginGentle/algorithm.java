package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/15-13:37
 */
public class Problem_1716_CalculateMoneyInLeetcodeBank {

    // 等差公式
    public static int totalMoney(int n) {
        int ans = 0;
        // 第一周可以存下来的钱
        int first = (1 + 7) * 7 / 2;
        int weeks = n / 7;
        // 最后一个完整周
        int last = first + (weeks - 1) * 7;
        ans += (first + last) * weeks / 2;
        // 剩下的不够一周的第一天存多少钱？
        int restDayOne = weeks + 1;
        for (int i = 1; i <= n % 7; i++) {
            ans += restDayOne - 1 + i;
        }
        return ans;
    }

}
