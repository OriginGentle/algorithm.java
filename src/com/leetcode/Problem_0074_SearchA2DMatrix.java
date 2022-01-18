package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/18-13:11
 */
public class Problem_0074_SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3}
        };
        int target = 3;
        boolean ans = searchMatrix(matrix, target);
        System.out.println(ans);
    }
}
