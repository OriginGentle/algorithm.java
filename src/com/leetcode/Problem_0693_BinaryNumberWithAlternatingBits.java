package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/28
 */
public class Problem_0693_BinaryNumberWithAlternatingBits {

    public static boolean hasAlternatingBits(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append((n & 1) == 0 ? "0" : "1");
            n >>= 1;
        }
        char[] str = sb.toString().toCharArray();
        for (int i = 0, j = 1; j < str.length; i++, j++) {
            if (str[i] == str[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 7;
        boolean ans = hasAlternatingBits(n);
        System.out.println(ans);
    }
}
