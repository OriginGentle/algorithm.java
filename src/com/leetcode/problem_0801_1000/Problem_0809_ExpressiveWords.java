package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/11/25-22:41
 */
public class Problem_0809_ExpressiveWords {

    public int expressiveWords(String s, String[] words) {
        int ans = 0;
        for (String word : words) {
            if (valid(s, word))
                ans++;
        }
        return ans;
    }

    public boolean valid(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }

            char ch = s.charAt(i);
            int ci = 0;
            while (i < s.length() && s.charAt(i) == ch) {
                i++;
                ci++;
            }

            int cj = 0;
            while (j < t.length() && t.charAt(j) == ch) {
                j++;
                cj++;
            }

            if (ci < cj || (ci != cj && ci < 3))
                return false;
        }
        return i == s.length() && j == t.length();
    }
}
