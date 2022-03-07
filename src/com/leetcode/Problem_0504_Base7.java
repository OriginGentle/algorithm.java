package com.leetcode;

/**
 * @author ycb
 * @since 2022/3/7-8:46
 */
public class Problem_0504_Base7 {

    public static String convertToBase7(int num) {
        if (num == 0) {
            return String.valueOf(num);
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = num < 0;
        num = Math.abs(num);
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        return flag ? "-" + sb.reverse().toString() : sb.reverse().toString();
    }

    public static void main(String[] args) {
        int num = 100;
        System.out.println(convertToBase7(num));
    }
}
