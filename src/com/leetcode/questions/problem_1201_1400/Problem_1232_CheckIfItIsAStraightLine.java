package com.leetcode.questions.problem_1201_1400;

/**
 * @author ycb
 * @since 2022/3/21-9:34
 */
public class Problem_1232_CheckIfItIsAStraightLine {

    public boolean checkStraightLine(int[][] c) {
        int n = c.length;
        if (n == 1 || n == 2) {
            return true;
        }
        int dx = c[0][0], dy = c[0][1];
        for (int i = 0; i < n; i++) {
            c[i][0] -= dx;
            c[i][1] -= dy;
        }
        int A = c[1][1], B = -c[1][0];
        for (int i = 2; i < n; i++) {
            int x = c[i][0], y = c[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
