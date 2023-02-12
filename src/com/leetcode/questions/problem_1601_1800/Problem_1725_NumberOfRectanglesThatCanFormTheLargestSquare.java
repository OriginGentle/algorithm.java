package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @since 2022/2/4-21:27
 */
public class Problem_1725_NumberOfRectanglesThatCanFormTheLargestSquare {

    public static int countGoodRectangles(int[][] rectangles) {
        int max = 0, ans = 0;
        for (int[] r : rectangles) {
            int cur = Math.min(r[0], r[1]);
            if (cur == max) {
                ans++;
            } else if (cur > max) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }
}
