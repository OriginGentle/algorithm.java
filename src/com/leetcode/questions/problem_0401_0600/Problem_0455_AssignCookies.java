package com.leetcode.questions.problem_0401_0600;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/9/10-20:41
 */
public class Problem_0455_AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int ans = 0;
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            while (j < n && g[i] > s[j]) {
                j++;
            }

            if (j < n) {
                ans++;
            }
        }
        return ans;
    }
}
