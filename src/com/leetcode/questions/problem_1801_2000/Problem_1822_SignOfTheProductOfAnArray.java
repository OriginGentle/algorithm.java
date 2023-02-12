package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @date 2022/3/20-20:25
 */
public class Problem_1822_SignOfTheProductOfAnArray {

    public int arraySign(int[] nums) {
        int count = 0;
        for (int n : nums) {
            if (n == 0) {
                return 0;
            }
            if (n < 0) {
                count++;
            }
        }
        return (count & 1) == 0 ? 1 : -1;
    }
}
