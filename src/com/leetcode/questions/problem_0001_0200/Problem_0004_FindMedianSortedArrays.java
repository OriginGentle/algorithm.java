package com.leetcode.questions.problem_0001_0200;

/**
 * @Author ycb
 * @Date 2021/6/20-19:30
 */
public class Problem_0004_FindMedianSortedArrays {

    // 归并排序 时间复杂度:O(M + N) 额外空间复杂度:O(M + N)
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }
        int N = nums1.length;
        int M = nums2.length;
        int index = 0;
        double ans = 0;
        double[] help = new double[N + M];
        int p1 = 0;
        int p2 = 0;
        while (p1 < N && p2 < M) {
            if (nums1[p1] > nums2[p2]) {
                help[index++] = nums2[p2++];
            } else {
                help[index++] = nums1[p1++];
            }
        }
        while (p1 < N) {
            help[index++] = nums1[p1++];
        }
        while (p2 < M) {
            help[index++] = nums2[p2++];
        }
        if (help.length % 2 == 0) {
            ans = (help[help.length / 2] + help[help.length / 2 - 1]) / 2;
        } else {
            ans = help[help.length / 2];
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

}
