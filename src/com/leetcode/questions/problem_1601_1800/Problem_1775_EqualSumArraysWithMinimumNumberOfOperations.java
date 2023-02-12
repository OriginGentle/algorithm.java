package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/7-23:22
 */
public class Problem_1775_EqualSumArraysWithMinimumNumberOfOperations {

    public static int minOperations(int[] nums1, int[] nums2) {
        if (6 * nums1.length < nums2.length ||
                6 * nums2.length < nums1.length)
            return -1;

        int d = 0;

        for (int num : nums2) d += num;

        for (int num : nums1) d -= num;

        if (d == 0) return 0;
        if (d < 0) {
            d = -d;
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        int[] cnt = new int[6];
        for (int num : nums1) cnt[6 - num]++;

        for (int num : nums2) cnt[num - 1]++;

        for (int i = 5, ans = 0; ; --i) {
            if (i * cnt[i] >= d)
                return ans + (d + i - 1) / i;

            ans += cnt[i];
            d -= i * cnt[i];
        }
    }
}
