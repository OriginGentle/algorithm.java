package com.weekly.code_2023_01_1_week;

/**
 * @author ycb
 * @date 2023/1/6-08:38
 * @desc 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
 * 请你找到这个数组里第 k 个缺失的正整数。
 * 测试链接 : https://leetcode.cn/problems/kth-missing-positive-number/
 */
public class Code01_KthMissingPositiveNumber {

    public static int findKthPositive(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        int m = 0, find = arr.length;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] - (m + 1) >= k) {
                r = m - 1;
                find = m;
            } else {
                l = m + 1;
            }
        }
        int preVal = find == 0 ? 0 : arr[find - 1];
        int under = preVal - find;
        return preVal + (k - under);
    }
}
