package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/3-21:04
 */
public class Problem_1796_SecondLargestDigitInAString {

    public static int secondHighest(String s) {
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int num = s.charAt(i) - '0';
                if (num > first) {
                    second = first;
                    first = num;
                } else if (num < first && num > second) {
                    second = num;
                }
            }
        }
        return second;
    }
}
