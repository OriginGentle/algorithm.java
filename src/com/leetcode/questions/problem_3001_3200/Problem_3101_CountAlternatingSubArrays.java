package com.leetcode.questions.problem_3001_3200;

/**
 * @author ycb
 * @date 2024/7/6-20:59
 */
public class Problem_3101_CountAlternatingSubArrays {

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        int  cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] != nums[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }

            ans += cnt;
        }

        return ans;
    }
}
