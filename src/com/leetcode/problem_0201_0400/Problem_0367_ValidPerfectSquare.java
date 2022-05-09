package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @since 2021/11/4-8:31
 */
public class Problem_0367_ValidPerfectSquare {

    public static boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        int L = 1;
        int R = num / 2;
        while (L <= R) {
            int M = (L + R) / 2;
            if (Math.pow(M, 2) == num) {
                return true;
            } else if (Math.pow(M, 2) < num) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int sqrt = (int) Math.sqrt(808201);
        System.out.println(sqrt);
        isPerfectSquare(808201);

        System.out.println(202050 * 202050);

        System.out.println(Math.pow(202050, 2));
    }
}
