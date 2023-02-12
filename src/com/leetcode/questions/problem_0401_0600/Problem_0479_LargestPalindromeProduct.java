package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/12/15-15:45
 */
public class Problem_0479_LargestPalindromeProduct {

    public static int largestPalindrome(int n) {
        if (n == 1)
            return 9;

        int max = (int) Math.pow(10, n) - 1;
        int ans = 0;
        for (int left = max; ans == 0; left--) { // 枚举回文的左半部分
            long p = left;
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + x % 10; // 翻转左半部分到末尾，构造回文数 p
            }

            for (long x = max; x * x >= p; x--) {
                if (p % x == 0) {
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }
}
