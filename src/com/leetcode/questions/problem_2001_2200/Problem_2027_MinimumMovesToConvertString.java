package com.leetcode.questions.problem_2001_2200;

/**
 * @author ycb
 * @date 2022/12/27-08:23
 */
public class Problem_2027_MinimumMovesToConvertString {

    public static int minimumMoves(String s) {
        int cover = -1, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X' && i > cover) {
                ans++;
                cover = i + 2;
            }
        }
        return ans;
    }
}
