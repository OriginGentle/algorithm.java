package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/28-08:29
 */
public class Problem_1750_MinimumLengthOfStringAfterDeletingSimilarEnds {

    public static int minimumLength(String s) {
        int L = 0, R = s.length() - 1;
        while (L < R && s.charAt(L) == s.charAt(R)) {
            char cur = s.charAt(L);
            while (L <= R && s.charAt(L) == cur) {
                L++;
            }
            while (L <= R && s.charAt(R) == cur) {
                R--;
            }
        }
        return R - L + 1;
    }
}
