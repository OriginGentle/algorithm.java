package com.leetcode;

/**
 * @author ycb
 * @since 2022/3/3-8:25
 */
public class Problem_0258_AddDigits {

    public static int addDigits1(int num) {
        if (num < 10) {
            return num;
        }

        while (num >= 10) {
            char[] str = String.valueOf(num).toCharArray();
            int sum = 0;
            for (char c : str) {
                sum += c - '0';
            }
            num = sum;
        }
        return num;
    }

    /*
    ====================================================================================================================
     */

    public static int addDigits2(int num) {
        if (num < 10) {
            return num;
        }
        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        int times = 100;
        for (int i = 0; i < times; i++) {
            int cur = (int) (Math.random() * 100);
            int ans = addDigits1(cur);
            System.out.println("当前数：" + cur + ",答案：" + ans);
        }
    }
}
