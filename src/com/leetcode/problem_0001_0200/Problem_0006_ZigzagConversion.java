package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/11/2-16:40
 */
public class Problem_0006_ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                res.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    res.append(s.charAt(j + cycleLen - i));
            }
        }
        return res.toString();
    }
}
