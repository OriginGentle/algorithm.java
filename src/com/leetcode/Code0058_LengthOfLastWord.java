package com.leetcode;

/**
 * @author ycb
 * @since 2021/9/21-11:17
 */
public class Code0058_LengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        if (s.length() == 1) {
            return s.charAt(0) == ' ' ? 0 : 1;
        }
        int L = s.length() - 1;
        while (s.charAt(L) == ' ') {
            L--;
        }
        int R = L;
        while (R >= 0 && s.charAt(R) != ' ') {
            R--;
        }
        return L - R;
    }

    public static void main(String[] args) {
        String s = "n ";
        System.out.println(lengthOfLastWord(s));
    }

}
