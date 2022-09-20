package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/20-10:52
 */
public class Problem_0240_SearchA2dMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
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
}
