package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @since 2022/2/23-8:26
 */
public class Problem_0917_ReverseOnlyLetters {

    public static String reverseOnlyLetters(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        for (int i = 0, j = n - 1; i < j; ) {
            while (i < j && !Character.isLetter(str[i])) {
                i++;
            }
            while (i < j && !Character.isLetter(str[j])) {
                j--;
            }
            if (i < j) swap(str, i++, j--);
        }
        return String.valueOf(str);
    }

    public static void swap(char[] str, int left, int right) {
        char tmp = str[left];
        str[left] = str[right];
        str[right] = tmp;
    }
}
