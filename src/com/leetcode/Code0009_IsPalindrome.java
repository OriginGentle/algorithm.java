package com.leetcode;

/**
 * @author ycb
 * @since 2021/8/25-20:15
 */
public class Code0009_IsPalindrome {

    public static boolean isPalindrome(int number) {
        if (number <= 0) {
            return false;
        }
        char[] str = String.valueOf(number).toCharArray();
        int L = 0;
        int R = str.length - 1;
        boolean ans = true;
        while (L < R) {
            if (str[L] != str[R]) {
                ans = false;
                break;
            }
            L++;
            R--;
        }
        return ans;
    }

    public static void main(String[] args) {
        boolean palindrome = isPalindrome(10);
        System.out.println(palindrome);
    }
}
