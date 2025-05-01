package com.leetcode.questions.problem_2801_3000;

/**
 * @author ycb
 * @date 2025/4/29-20:27
 */
public class Process_2962_CountSubArraysWhereMaxElementAppearsAtLeastKTimes {

    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int  max = 0, cntMax = 0, L = 0;

        for (int num : nums) {
            max = Math.max(num, max);
        }

        for (int num : nums) {

            if (num == max)
                cntMax++;

            while (cntMax == k) {
                if (nums[L] == max)
                    cntMax--;

                L++;
            }

            ans += L;
        }
        return ans;
    }
}
