package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/13-14:38
 */
public class Problem_0791_CustomSortString {

    public String customSortString(String order, String s) {
        int[] cmt = new int[26];
        for (int i = 0; i < s.length(); i++)
            ++cmt[s.charAt(i) - 'a'];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            while (cmt[c - 'a'] > 0) {
                sb.append(c);
                cmt[c - 'a']--;
            }
        }

        for (int i = 0; i < 26; i++) {
            while (cmt[i] > 0) {
                sb.append((char) (i + 'a'));
                cmt[i]--;
            }
        }
        return sb.toString();
    }
}
