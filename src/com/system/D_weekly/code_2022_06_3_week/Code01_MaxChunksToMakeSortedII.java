package com.system.D_weekly.code_2022_06_3_week;

/**
 * @author ycb
 * @date 2022/6/26-14:05
 * @desc https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
 */
public class Code01_MaxChunksToMakeSortedII {

    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] min = new int[n];
        min[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(arr[i], min[i + 1]);
        }
        int ans = 1;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (max <= min[i]) {
                ans++;
            }
            max = Math.max(max, arr[i]);
        }
        return ans;
    }
}
