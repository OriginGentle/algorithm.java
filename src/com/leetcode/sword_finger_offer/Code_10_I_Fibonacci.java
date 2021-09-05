package com.leetcode.sword_finger_offer;

/**
 * @author ycb
 * @since 2021/9/4-11:00
 */
public class Code_10_I_Fibonacci {

    // 暴力解
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 2 || n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /*
    ====================================================================================================================
     */


}
