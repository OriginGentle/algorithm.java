package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @since 2022/2/2-11:03
 */
public class Problem_2000_ReversePrefixOfWord {

    public static String reversePrefix(String word, char ch) {
        int n = word.length();
        int pos = -1;
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) == ch) {
                pos = i;
                break;
            }
        }
        return reverse(word, 0, pos);
    }

    private static String reverse(String word, int start, int end) {
        char[] str = word.toCharArray();
        while (start <= end) {
            char ch = str[start];
            str[start++] = str[end];
            str[end--] = ch;
        }
        return String.valueOf(str);
    }
}
