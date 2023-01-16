package com.system.IV_weekly.code_2022_02_2_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/2/11-18:23
 * @description https://leetcode.com/problems/recover-the-original-array/
 */
public class Code05_RecoverTheOriginalArray {

    // N ^ 2
    public static int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        // nums[0]一定是小数组的第一个
        int n = nums.length;
        int m = n >> 1;
        // 谁是大数组的第一个?不知道,试!
        for (int first = 1; first <= m; first++) {
            int d = nums[first] - nums[0];
            // d为正整数
            if (d > 0 && (d & 1) == 0) {
                int[] ans = new int[m];
                int i = 0;
                boolean[] set = new boolean[n];
                int k = d >> 1;
                int l = 0;
                int r = first;
                while (r < n) {
                    while (set[l]) {
                        l++;
                    }
                    if (l == r) {
                        r++;
                    } else if (nums[r] - nums[l] > d) {
                        break;
                    } else if (nums[r] - nums[l] < d) {
                        r++;
                    } else {
                        set[r++] = true;
                        ans[i++] = nums[l++] + k;
                    }
                }
                if (i == m) {
                    return ans;
                }
            }

        }
        return null;
    }
}
