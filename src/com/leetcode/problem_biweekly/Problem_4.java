package com.leetcode.problem_biweekly;

public class Problem_4 {

    public long minimumReplacement(int[] nums) {
        long ans = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                int cnt = nums[i] / nums[i + 1] + (nums[i] % nums[i + 1] == 0 ? 0 : 1);
                ans += cnt - 1;
                nums[i] /= cnt;
            }
        }
        return ans;
    }
}
