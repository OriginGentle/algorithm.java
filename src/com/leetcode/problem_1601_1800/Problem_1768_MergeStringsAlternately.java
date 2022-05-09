package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/3/25-16:22
 * @desc
 */
public class Problem_1768_MergeStringsAlternately {

    public static String mergeAlternately(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        char[] ans = new char[n + m];
        int ai = 0, i = 0, j = 0;
        while (ai != m + n) {
            if ((ai & 1) == 0 && i < n) {
                ans[ai] = word1.charAt(i++);
            } else if (((ai | 1) != 0 && j < m)) {
                ans[ai] = word2.charAt(j++);
            } else if (i >= n && j < m) {
                ans[ai] = word2.charAt(j++);
            } else if (j >= m && i < n) {
                ans[ai] = word1.charAt(i++);
            }
            ai++;
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "12345";
        String ans = mergeAlternately(s1, s2);
        System.out.println(ans);
    }
}
