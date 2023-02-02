package com.leetcode.problem_interview;

/**
 * @author ycb
 * @date 2022/9/26-13:55
 */
public class Problem_17_19_MissingTwoLCCI {

    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int sum = n * (1 + n) / 2;

        for (int x : nums)
            sum -= x;

        int m = sum / 2, rest = sum;
        sum = m * (1 + m) / 2;

        for (int num : nums) {
            if (num <= m)
                sum -= num;
        }
        return new int[]{sum, rest - sum};
    }
}
