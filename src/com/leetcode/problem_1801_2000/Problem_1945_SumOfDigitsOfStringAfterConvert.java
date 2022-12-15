package com.leetcode.problem_1801_2000;

/**
 * @author ycb
 * @date 2022/12/15-08:53
 */
public class Problem_1945_SumOfDigitsOfStringAfterConvert {

    public static int getLucky(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i) - 'a' + 1);
        }
        String str = sb.toString();
        for (int i = 0; i < k && str.length() > 1; i++) {
            int bitOfSum = 0;
            for (int j = 0; j < str.length(); j++) {
                bitOfSum += str.charAt(j) - '0';
            }
            str = String.valueOf(bitOfSum);
        }
        return Integer.parseInt(str);
    }
}
