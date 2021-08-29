package com.training.day09;

/**
 * @author ycb
 * @date 2021/8/24-8:22
 * @description https://leetcode.com/problems/longest-increasing-subsequence
 */
public class Code03_LIS {

    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // end[i] : 代表目前所有长度为i+1的递增子序列的最大长度
        int[] end = new int[arr.length];
        end[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            end[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

}
