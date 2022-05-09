package com.leetcode.problem_2001_2200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @since 2022/3/4-8:24
 */
public class Problem_2104_SumOfSubarrayRanges {

    public static long subArrayRanges1(int[] nums) {
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

    public static long subArrayRanges2(int[] nums) {
        int n = nums.length;
        // min[i] 为 nums[i] 作为区间最小值的次数；max[i] 为 nums[i] 作为区间最大值的次数
        long[] min = getCnt(nums, true), max = getCnt(nums, false);
        long ans = 0;
        for (int i = 0; i < n; i++) ans += (max[i] - min[i]) * nums[i];
        return ans;
    }

    private static long[] getCnt(int[] nums, boolean isMin) {
        int n = nums.length;
        int[] a = new int[n], b = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] >= nums[i] : nums[d.peekLast()] <= nums[i]))
                d.pollLast();
            a[i] = d.isEmpty() ? -1 : d.peekLast();
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!d.isEmpty() && (isMin ? nums[d.peekLast()] > nums[i] : nums[d.peekLast()] < nums[i])) d.pollLast();
            b[i] = d.isEmpty() ? n : d.peekLast();
            d.addLast(i);
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) ans[i] = (i - a[i]) * 1L * (b[i] - i);
        return ans;
    }
}
