package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/19-10:36
 */
public class Problem_0223_RectangleArea {

    public static int computeArea(int ax1, int ay1, int ax2, int ay2,
                           int bx1, int by1, int bx2, int by2) {

        int x = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int y = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - (x * y);
    }
}
