package com.system.D_weekly.code_2022_01_2_week;

/**
 * @author ycb
 * @date 2022/1/15-12:17
 * @description 周赛第五题
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/
 */
public class Code04_MinimumOperationsToMakeTheArrayKIncreasing {

    // 前置：最长递增子序列
    public static int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        // 向上取整
        int[] help = new int[(n + k - 1) / k];
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += needs(arr, help, n, i, k);
        }
        return ans;
    }

    // arr[start , start + k, start + 2k, start + 3k,....]
    // 辅助数组help，为了求最长递增子序列
    // 上面的序列，要改几个数，能都有序！
    private static int needs(int[] arr, int[] help, int n, int start, int k) {
        int j = 0, size = 0;
        for (; start < n; start += k, j++) {
            size = insert(help, size, arr[start]);
        }
        return j - size;
    }

    private static int insert(int[] help, int size, int num) {
        int l = 0;
        int r = size - 1;
        int m = 0;
        int ans = size;
        while (l <= r) {
            m = (l + r) / 2;
            if (help[m] > num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        help[ans] = num;
        return ans == size ? size + 1 : size;
    }
}
