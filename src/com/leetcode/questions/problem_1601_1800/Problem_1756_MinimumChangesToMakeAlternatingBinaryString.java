package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/29-09:51
 */
public class Problem_1756_MinimumChangesToMakeAlternatingBinaryString {

    public static int minOperations(String s) {
        int cnt = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            cnt += (s.charAt(i) - '0') ^ (i & 1);
        }
        return Math.min(cnt, n - cnt);
    }
}
