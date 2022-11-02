package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/10/10-08:38
 */
public class Problem_0801_MinimumSwapsToMakeSequencesIncreasing {

    public static int minSwap1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            int a1 = nums1[i - 1], a2 = nums1[i];
            int b1 = nums1[i - 1], b2 = nums2[i];

            if ((a1 < a2 && b1 < b2) && (b1 < a2 && a1 < b2)) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i][0] + 1;
            } else if (a1 < a2 && b1 < b2) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    /*
    ====================================================================================================================
     */

    public static int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int noSwap = 0, swap = 1;
        for (int i = 1; i < n; i++) {
            int ns = n, ys = n;
            if (nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i]) {
                ns = noSwap;
                ys = swap + 1;
            }
            if (nums2[i - 1] < nums1[i] && nums1[i - 1] < nums2[i]) {
                ns = Math.min(ns, swap);
                ys = Math.min(ys, noSwap + 1);
            }
            noSwap = ns;
            swap = ys;
        }
        return Math.min(noSwap, swap);
    }
}
