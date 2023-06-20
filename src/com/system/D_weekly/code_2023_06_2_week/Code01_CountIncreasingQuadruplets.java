package com.system.D_weekly.code_2023_06_2_week;

/**
 * @author ycb
 * @date 2023/6/19-08:57
 * @desc 给你一个长度为 n 下标从 0 开始的整数数组 nums
 * 它包含 1 到 n 的所有数字，请你返回上升四元组的数目。
 * 如果一个四元组 (i, j, k, l) 满足以下条件，我们称它是上升的：
 * 0 <= i < j < k < l < n 且
 * nums[i] < nums[k] < nums[j] < nums[l] 。
 * 测试链接 : https://leetcode.cn/problems/count-increasing-quadruplets/
 */
public class Code01_CountIncreasingQuadruplets {

    public static long countQuadruplets1(int[] nums) {
        int n = nums.length;
        long ans = 0;
        // dp[j]的含义:
        // 假设当前来到 l 位置
        // 那么 l 之前的范围上
        // 0 ... i ... j ... k ... l - 1
        // 如果 j 作为中间点，有多少三元组满足: arr[i] < arr[k] < arr[j]
        long[] dp = new long[n];
        for (int l = 1; l < n; l++) {

            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    ans += dp[j];
                }
            }

            // dp[0...l-1]上的所有信息，有效的范围 : 0 .... l-1
            // dp[0...l-1]，扩充有效范围: 0........l
            // 目前比[l]数小的数的个数
            int cnt = 0;
            for (int j = 0; j < l; j++) {

                if (nums[j] < nums[l]) {
                    cnt++;
                } else {
                    dp[j] += cnt;
                }
            }

        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static long countQuadruplets2(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n];
        long ans = 0;
        for (int l = 1; l < n; l++) {
            int cnt = 0;
            for (int j = 0; j < l; j++) {
                if (nums[j] < nums[l]) {
                    ans += dp[j];
                    cnt++;
                } else {
                    dp[j] += cnt;
                }
            }
        }
        return ans;
    }
}
