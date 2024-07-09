package com.leetcode.questions.problem_2801_3000;

/**
 * @author ycb
 * @date 2024/7/10-00:18
 */
public class Problem_2970_CountTheNumberOfIncremovableSubArraysI {

    public static int incremovableSubarrayCount(int[] nums) {
        int ans, n = nums.length, l = 1;

        while (l < n && nums[l - 1] < nums[l]) {
            l++;
        }

        ans = l + (l < n ? 1 : 0);

        for (int r = n - 2; r >= 0; r--) {

            while (l > 0 && nums[l - 1] >= nums[r + 1]) {
                l--;
            }

            ans += l + (l <= r ? 1 : 0);

            if (nums[r] >= nums[r + 1]) {
                break;
            }
        }

        return ans;
    }
}
