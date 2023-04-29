package com.system.D_weekly.code_2023_04_4_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2023/4/29-00:39
 * @desc 给你一个正整数数组nums, 同时给你一个长度为 m 的整数数组 queries
 * 第 i 个查询中，你需要将 nums 中所有元素变成 queries[i] 。你可以执行以下操作 任意 次：
 * 将数组里一个元素 增大 或者 减小 1 。请你返回一个长度为 m 的数组 answer ，
 * 其中 answer[i]是将 nums 中所有元素变成 queries[i] 的 最少 操作次数。
 * 注意，每次查询后，数组变回最开始的值。
 * 测试链接 : https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/
 */
public class Code02_MinOpToMakeElementsEqual {

    public static List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        // 一个数都不选的时候，前缀和等于 0
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        List<Long> ans = new ArrayList<>();
        int less, more;
        long curAns;
        for (int v : queries) {
            less = bs(nums, v);
            curAns = (long) (less + 1) * v - getSum(sum, 0, less);
            more = bs(nums, v + 1);
            curAns += getSum(sum, more + 1, n - 1) - (long) (n - more - 1) * v;
            ans.add(curAns);
        }
        return ans;
    }

    // 查找 <v 的最右位置
    public static int bs(int[] nums, int v) {
        int l = 0, r = nums.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < v) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static long getSum(long[] sum, int l, int r) {
        return l > r ? 0 : sum[r + 1] - sum[l];
    }
}
