package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @since 2022/1/25-8:21
 */
public class Problem_1688_CountOfMatchesInTournament {

    public static int numberOfMatches1(int n) {
        int ans = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                ans += n / 2;
                n /= 2;
            } else {
                ans += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int numberOfMatches2(int n) {
        return n - 1;
    }
}
