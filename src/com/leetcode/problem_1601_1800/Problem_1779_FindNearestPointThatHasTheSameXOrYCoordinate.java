package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/3/19-16:46
 */
public class Problem_1779_FindNearestPointThatHasTheSameXOrYCoordinate {

    public int nearestValidPoint(int x, int y, int[][] points) {
        int mhd = 10000;
        int ans = -1;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x) {
                if (Math.abs(y - points[i][1]) < mhd) {
                    mhd = Math.abs(y - points[i][1]);
                    ans = i;
                }
            }
            if (points[i][1] == y) {
                if (Math.abs(x - points[i][0]) < mhd) {
                    mhd = Math.abs(x - points[i][0]);
                    ans = i;
                }
            }

        }
        return ans;
    }
}
