package com.leetcode.problem_1201_1400;

/**
 * @author ycb
 * @date 2022/3/27
 */
public class Problem_1309_DecryptStringFromAlphabetToIntegerMapping {

    public static String str = "abcdefghijklmnopqrstuvwxyz";

    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] map = str.toCharArray();
        for (int i = 0; i < s.length(); ) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                sb.append(map[Integer.valueOf(s.substring(i, i + 2)) - 1]);
                i += 3;
            } else {
                sb.append((char) (map[s.charAt(i) - '0'] - 1));
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "10#11#12";
        String ans = freqAlphabets(str);
        System.out.println(ans);
    }
}
