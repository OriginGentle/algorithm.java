package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/7/5-10:37
 */
public class Problem_2600_KItemsWithTheMaximumSum {

    public static int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        } else if (k <= numOnes + numZeros) {
            return numOnes;
        } else {
            return numOnes - (k - numOnes - numZeros);
        }
    }
}
