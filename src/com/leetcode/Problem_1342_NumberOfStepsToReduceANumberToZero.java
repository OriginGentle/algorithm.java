package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/31-1:20
 */
public class Problem_1342_NumberOfStepsToReduceANumberToZero {

    public int numberOfSteps(int num) {
        int ans = 0;
        while (num != 0) {
            num = num % 2 == 0 ? num / 2 : num - 1;
            ans++;
        }
        return ans;
    }
}
