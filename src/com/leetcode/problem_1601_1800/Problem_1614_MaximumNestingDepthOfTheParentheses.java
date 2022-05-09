package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @since 2022/1/7-8:21
 */
public class Problem_1614_MaximumNestingDepthOfTheParentheses {

    public static int maxDepth(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int size = 0, ans = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                ++size;
                ans = Math.max(ans, size);
            } else if (ch == ')') {
                --size;
            }
        }
        return ans;
    }
}
