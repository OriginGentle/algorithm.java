package com.leetcode;

/**
 * @author ycb
 * @since 2021/9/23-11:26
 */
public class Code326_IsPowerOfThree {

    public static boolean isPowerOfThree(int n) {
        return n > 0 && Math.pow(3, 19) % n == 0;
    }

}
