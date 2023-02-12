package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/20-20:55
 */
public class Problem_1760_MinimumLimitOfBallsInABag {

    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = 0;
        for (int num : nums) {
            right = Math.max(num, right);
        }

        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long op = 0;
            for (int num : nums) {
                op += (num - 1) / mid;
            }

            if (op <= maxOperations) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
