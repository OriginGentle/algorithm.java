package com.basic.day25;

/**
 * @Author ycb
 * @Date 2021/5/31-15:39
 * @Description https://leetcode.com/problems/maximal-rectangle/
 * 压缩数组和单调栈 --> 直方图问题
 */
public class Code04_MaximalRectangle {

    public static int maximaRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 以每一行作底，看做直方图问题去解决
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(height));
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int N = heights.length;
        int[] stack = new int[N];
        int si = -1;
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (si != -1 && heights[i] <= heights[stack[si]]) {
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i - k - 1) * heights[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (heights.length - k - 1) * heights[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}
