package com.leetcode.problem_0801_1000;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/11/18-22:25
 */
public class Problem_0891_SumOfSubsequenceWidths {

    public static final int MOD = (int) 1e9 + 7;

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long ans = 0L, pow2 = 1L;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ans += (nums[i] - nums[n - 1 - i]) * pow2;
            pow2 = pow2 * 2 % MOD;
        }
        return (int) (ans % MOD + MOD) % MOD;
    }
}
