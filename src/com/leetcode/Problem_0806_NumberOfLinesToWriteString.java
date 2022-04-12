package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/12
 */
public class Problem_0806_NumberOfLinesToWriteString {

    static int MAX_WIDTH = 100;

    public static int[] numberOfLines(int[] widths, String s) {
        int row = 1;
        int curWidth = 0;
        char[] str = s.toCharArray();
        for (char c : str) {
            int need = widths[c - 'a'];
            curWidth += need;
            if (curWidth > MAX_WIDTH) {
                row++;
                curWidth = need;
            }
        }
        return new int[]{row, curWidth};
    }
}
