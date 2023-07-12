package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/7/12-09:00
 */
public class Problem_2544_AlternatingDigitSum {

    public static int alternateDigitSum(int n) {
        int len = getLenOfNum(n);
        int offset = (int) Math.pow(10, len - 1);
        int ans = 0;

        for (int j = 1; j <= len; j++) {
            int bit = n / offset % 10;
            ans += (j % 2 != 0 ? bit : -bit);
            n -= bit * offset;
            offset /= 10;
        }

        return ans;
    }

    public static int getLenOfNum(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }
}
