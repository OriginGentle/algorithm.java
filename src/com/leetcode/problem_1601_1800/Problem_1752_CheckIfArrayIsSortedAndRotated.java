package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/27-02:35
 */
public class Problem_1752_CheckIfArrayIsSortedAndRotated {

    public boolean check(int[] nums) {
        int n = nums.length, tmp = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                tmp = i;
                break;
            }
        }

        if (tmp == 0)
            return true;

        for (int i = tmp + 1; i < n; i++) {
            if (nums[i] < nums[i - 1])
                return false;
        }

        return nums[0] >= nums[n - 1];
    }
}
