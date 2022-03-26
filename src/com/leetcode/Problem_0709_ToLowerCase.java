package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/26
 */
public class Problem_0709_ToLowerCase {

    public static String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 65 && c <= 90) {
                c |= 32;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Hello";
        String ans = toLowerCase(s);
        System.out.println(ans);
    }
}
