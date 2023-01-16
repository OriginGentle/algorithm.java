package com.system.III_training.day04;

/**
 * @Author ycb
 * @Date 2021/8/1-17:26
 * @Description https://leetcode.com/problems/maximum-subarray/
 */
public class Code02_SubArrayMaxSum {

    public static int maxSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 动态规划
        // 当前来到i位置，看i-1求出的答案
        int max = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[i], pre + arr[i]);
            max = Math.max(max, pre);
        }
        return max;
    }
}
