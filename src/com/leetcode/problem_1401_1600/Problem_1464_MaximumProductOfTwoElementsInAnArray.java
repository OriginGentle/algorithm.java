package com.leetcode.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/8/26-09:52
 */
public class Problem_1464_MaximumProductOfTwoElementsInAnArray {

    public static int maxProduct(int[] nums) {
        int a = -1, b = -1;
        for (int num : nums) {
            if (num > a) {
                b = a;
                a = num;
            } else if (num > b)
                b = num;
        }
        return (a - 1) * (b - 1);
    }
}
