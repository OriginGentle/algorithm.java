package com.training.day04;

/**
 * @Author ycb
 * @Date 2021/8/1-17:27
 * @Description 返回一个二维数组中，子矩阵最大累加和
 */
public class Code03_SubMatrixMaxSum {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        // 把所有行进行全排列，找最大的子矩阵最大累加和
        // 数组压缩技巧 : 时间复杂度 O(N ^ 2 * M)
        // 优化点: 行数和列数比，谁小选谁
        for (int i = 0; i < N; i++) {
            int[] arr = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    arr[k] += m[j][k];
                }
                max = Math.max(max, maxSubArray(arr));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    // 加强版:https://leetcode-cn.com/problems/max-submatrix-lcci/
    public int[] getMaxMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int cur;
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < N; i++) {
            int[] arr = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    arr[k] += matrix[j][k];
                    cur += arr[k];
                    if (cur > max) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[]{a, b, c, d};
    }

}
