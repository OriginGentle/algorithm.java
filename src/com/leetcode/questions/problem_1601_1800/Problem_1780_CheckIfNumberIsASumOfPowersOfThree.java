package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/9-23:36
 */
public class Problem_1780_CheckIfNumberIsASumOfPowersOfThree {

    public static boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 2)
                return false;
            n /= 3;
        }
        return true;
    }
}
