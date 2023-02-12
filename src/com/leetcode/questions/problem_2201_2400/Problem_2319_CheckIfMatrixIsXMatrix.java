package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/1/31-23:39
 */
public class Problem_2319_CheckIfMatrixIsXMatrix {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i + j) == (n - 1)) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
