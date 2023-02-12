package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/3/26
 */
public class Problem_0389_FindTheDifference {

    public static char findTheDifference(String s, String t) {
        if (s == null || s.length() == 0) {
            return t.charAt(0);
        }
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        Character ans = null;
        for (char c : t.toCharArray()) {
            if (count[c - 'a'] == 0 || --count[c - 'a'] < 0) {
                ans = c;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String t = "abcde";
        char ans = findTheDifference(s, t);
        System.out.println(ans);
    }
}
