package com.leetcode;

/**
 * @author ycb
 * @since 2022/3/23-13:18
 */
public class Problem_1588_SumOfAllOddLengthSubarrays {

    public int sumOddLengthSubarrays1(int[] arr) {
        int n = arr.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        int ans = 0;
        // 枚举每个开头位置
        for (int s = 0; s < n; s++) {
            // 枚举s作为开头位置，子数组的长度
            for (int len = 1; s + len <= n; len += 2) {
                int end = s + len - 1;
                ans += preSum[end + 1] - preSum[s];
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public int sumOddLengthSubarrays2(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int leftCount = i;
            int rightCount = n - i - 1;
            int leftOdd = (leftCount + 1) / 2;
            int rightOdd = (rightCount + 1) / 2;
            int leftEven = leftCount / 2 + 1;
            int rightEven = rightCount / 2 + 1;
            sum += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return sum;
    }
}
