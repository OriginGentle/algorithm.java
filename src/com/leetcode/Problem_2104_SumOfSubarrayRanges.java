package com.leetcode;

/**
 * @author ycb
 * @since 2022/3/4-8:24
 */
public class Problem_2104_SumOfSubarrayRanges {

    public long subArrayRanges1(int[] nums) {
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public long subArrayRanges2(int[] nums) {

        return 0;
    }
}
