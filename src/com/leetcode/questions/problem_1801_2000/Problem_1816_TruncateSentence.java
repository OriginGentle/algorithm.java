package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @since 2021/12/6-8:28
 */
public class Problem_1816_TruncateSentence {

    public static String truncateSentence(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return null;
        }
        int end = 0;
        while (k > 0) {
            if (end == s.length() - 1) {
                return s;
            }
            if (s.charAt(end++) == ' ') {
                k--;
            }
        }
        return s.substring(0, end - 1);
    }

    public static void main(String[] args) {
        String s = "What is the solution to this problem";
        int k = 4;
        String ans = truncateSentence(s, k);
        System.out.println(ans);
    }
}
