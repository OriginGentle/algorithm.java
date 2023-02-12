package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/8/11-00:03
 */
public class Problem_1417_ReformatTheString {

    public static String reformat(String s) {
        int nc = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                nc++;
            }
        }
        int sc = s.length() - nc;
        if (Math.abs(nc - sc) > 1) {
            return "";
        }
        boolean flag = nc > sc;
        char[] str = s.toCharArray();

        for (int i = 0, j = 1; i < str.length; i += 2) {
            if (Character.isDigit(str[i]) != flag) {
                while (Character.isDigit(str[j]) != flag) {
                    j += 2;
                }
                swap(str, i, j);
            }
        }
        return new String(str);
    }

    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        char c = '0';
        System.out.println(Character.isDigit(c));
    }
}
