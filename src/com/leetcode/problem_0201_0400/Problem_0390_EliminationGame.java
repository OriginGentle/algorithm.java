package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @since 2022/1/2-13:49
 */
public class Problem_0390_EliminationGame {

    public static int lastRemaining(int n) {
        int ans = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) {
                ans = ans + step;
            } else {
                ans = (cnt % 2 == 0) ? ans : ans + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return ans;
    }
}
