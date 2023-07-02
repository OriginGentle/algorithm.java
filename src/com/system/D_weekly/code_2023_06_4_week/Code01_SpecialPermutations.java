package com.system.D_weekly.code_2023_06_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/7/1-13:47
 * @desc 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数
 * 如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列
 * 对于 0 <= i < n - 1 的下标 i
 * 要么 nums[i] % nums[i+1] == 0
 * 要么 nums[i+1] % nums[i] == 0
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 1000000007 取余 后返回
 * 测试链接 : https://leetcode.cn/problems/special-permutations/
 */
public class Code01_SpecialPermutations {

    public static int mod = 1000000007;

    public static int specialPerm(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], -1);
        }

        return process(nums, n, 0, 0, dp);
    }

    // s：表示状态
    // p：表示之前选择数字
    // 之前选的数字，一定相邻两个数，都合法！
    // 返回：根据上面的情况，继续把所有数字选完！返回有多少合法的！数量！
    private static int process(int[] nums, int n, int s, int p, int[][] dp) {
        if (dp[s][p] != -1) {
            return dp[s][p];
        }
        int ans = 0;
        if (s == (1 << n) - 1) {
            ans = 1;
        } else {
            for (int i = 0; i < n; i++) {
                if (s == 0 ||
                        (s & (1 << i)) == 0 && (nums[p] % nums[i] == 0 || nums[i] % nums[p] == 0)) {

                    ans = (ans + process(nums, n, (1 << i) | s, i, dp)) % mod;
                }
            }
        }
        dp[s][p] = ans;
        return ans;
    }
}
