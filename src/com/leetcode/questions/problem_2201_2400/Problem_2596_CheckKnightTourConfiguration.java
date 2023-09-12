package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/9/13-00:33
 */
public class Problem_2596_CheckKnightTourConfiguration {

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }

        int n = grid.length;
        int[][] idxs = new int[n * n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                idxs[grid[i][j]][0] = i;
                idxs[grid[i][j]][1] = j;
            }
        }

        for (int i = 1; i < n * n; i++) {
            int nc = Math.abs(idxs[i][0] - idxs[i - 1][0]);
            int nr = Math.abs(idxs[i][1] - idxs[i - 1][1]);

            if (nc * nr != 2) {
                return false;
            }
        }

        return true;
    }
}
