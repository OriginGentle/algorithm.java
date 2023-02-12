package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @since 2022/3/17-13:45
 */
public class Problem_1523_CountOddNumbersInAnIntervalRange {

    public int countOdds(int low, int high) {
        return ((high - low) / 2) + ((low & 1) | (high & 1));
    }
}
