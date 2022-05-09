package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/4/26
 */
public class Problem_0883_ProjectionAreaOf3dShapes {

    public static int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, xzArea = 0, yzArea = 0;
        for (int i = 0; i < n; i++) {
            int xzHeight = 0, yzHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] == 0 ? 0 : 1;
                yzHeight = Math.max(yzHeight, grid[i][j]);
                xzHeight = Math.max(xzHeight, grid[j][i]);
            }
            yzArea += yzHeight;
            xzArea += xzHeight;
        }
        return xyArea + xzArea + yzArea;
    }
}
