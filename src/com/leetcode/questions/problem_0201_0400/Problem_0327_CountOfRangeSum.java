package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/27-15:03
 */
public class Problem_0327_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }

        return process(preSum, 0, n - 1, lower, upper);
    }

    public static int process(long[] preSum, int L, int R, int lower, int upper) {
        if (L == R)
            return preSum[L] >= lower && preSum[R] <= upper ? 1 : 0;

        int mid = (L + R) >> 1;

        return process(preSum, L, mid, lower, upper) +
                process(preSum, mid + 1, R, lower, upper) +
                merger(preSum, L, mid, R, lower, upper);
    }

    public static int merger(long[] arr, int L, int M, int R, int low, int up) {
        int ans = 0;
        int wL = L, wR = L;

        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - up;
            long max = arr[i] - low;

            while (wR <= M && arr[wR] <= max)
                wR++;

            while (wL <= M && arr[wL] < min)
                wL++;

            ans += wR - wL;
        }

        long[] help = new long[R - L + 1];

        int idx = 0;
        int p1 = L, p2 = M + 1;

        while (p1 <= M && p2 <= R)
            help[idx++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];

        while (p1 <= M)
            help[idx++] = arr[p1++];

        while (p2 <= R)
            help[idx++] = arr[p2++];

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}
