package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/7/12-16:13
 */
public class Problem_0168_ExcelSheetColumnTitle {

    public static String convertToTitle(int cn) {
        StringBuffer sb = new StringBuffer();
        while (cn > 0) {
            cn--;
            sb.append((char) (cn % 26 + 'A'));
            cn /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
