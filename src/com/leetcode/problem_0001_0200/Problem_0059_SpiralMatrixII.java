package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/14-11:49
 */
public class Problem_0059_SpiralMatrixII {

    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int count = 1;
        // 分圈技巧
        // 从外向内一层一层的填
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            // 从左往右，把当前层的最上行填好
            for (int col = left; col <= right; col++) {
                ans[top][col] = count++;
            }
            // 从上往下，把当前层最右列填好
            for (int row = top + 1; row <= bottom; row++) {
                ans[row][right] = count++;
            }
            // 不是最后一层
            if (left < right && top < bottom) {
                // 从右往左，把当前层的最底行填好
                for (int col = right - 1; col > left; col--) {
                    ans[bottom][col] = count++;
                }
                // 从下往上，把当前层的最左列填好
                for (int row = bottom; row > top; row--) {
                    ans[row][left] = count++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }


    public static void main(String[] args) {
        int n = 5;
        int[][] matrix = generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
