package com.training.day29;

/**
 * @author ycb
 * @since 2021/10/16-12:06
 */
public class Problem_0073_SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        boolean col0 = false;
        int i = 0, j = 0;
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    if (j == 0) {
                        col0 = true;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        for (i = matrix.length - 1; i >= 0; i--) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (col0) {
            for (i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
