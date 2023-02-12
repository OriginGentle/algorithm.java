package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2022/6/8-08:24
 */
public class Problem_1037_ValidBoomerang {

    public boolean isBoomerang(int[][] points) {
        if (points == null || points.length != 3) {
            return false;
        }
        int[] v1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = {points[2][0] - points[0][0], points[2][1] - points[0][1]};
        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }
}
