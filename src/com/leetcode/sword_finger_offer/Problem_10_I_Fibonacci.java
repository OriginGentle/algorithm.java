package com.leetcode.sword_finger_offer;

/**
 * @author ycb
 * @since 2021/9/4-11:00
 */
public class Problem_10_I_Fibonacci {

    static final int mod = 1000000007;

    // 暴力解
    public static int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 2 || n == 1) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /*
    ====================================================================================================================
     */

    // 矩阵快速幂
    public static int fib2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    // 计算一个矩阵的n次方结果返回
    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        // 矩阵中1的概念
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, t);
            }
            t = muliMatrix(t, t);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    // |1 0|   |a b|
    // |0 1|   |c d|
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int ans = fib2(45);
        System.out.println(134903163);
        System.out.println(ans);
    }

}
