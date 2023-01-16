package com.system.IV_weekly.code_2022_11_5_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/12/4-15:37
 * @desc 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
 * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和
 * 由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
 * 子序列 定义为从一个数组里删除一些（或者不删除）元素，
 * 但不改变剩下元素的顺序得到的数组
 * 例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 * 测试链接 : https://leetcode.cn/problems/sum-of-subsequence-widths/
 */
public class Code03_SumOfSubSequenceWidths {

    public static final int MOD = (int) 1e9 + 7;

    public static int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long A = 0, B = 0, C = 1, D = C;
        long ans = 0;
        for (int i = 1; i < nums.length; i++) {
            A = (D * nums[i]) % MOD;
            B = (B * 2 + nums[i - 1]) % MOD;
            ans = (ans + A - B + MOD) % MOD;
            C = (C * 2) % MOD;
            D = (D + C) % MOD;
        }
        return (int) ans;
    }
}
