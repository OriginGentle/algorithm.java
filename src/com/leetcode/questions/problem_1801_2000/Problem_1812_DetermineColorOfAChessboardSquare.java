package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @date 2022/12/8-22:08
 */
public class Problem_1812_DetermineColorOfAChessboardSquare {

    public static boolean squareIsWhite(String coordinates) {
        int col = coordinates.charAt(0) - 'a' + 1;
        int row = coordinates.charAt(1) - '0';
        return (col + row) % 2 == 1;
    }

    public static void main(String[] args) {
        String s = "b2";
        boolean ans = squareIsWhite(s);
        System.out.println(ans);
    }
}
