package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/12
 * @desc
 */
public class Problem_0357_CountNumbersWithUniqueDigits {

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int ans = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            ans += cur;
        }
        return ans;
    }
}
