package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/16-09:59
 */
public class Problem_0775_GlobalAndLocalInversions {

    public boolean isIdealPermutation(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) >= 2)
                return false;
        }
        return true;
    }
}
