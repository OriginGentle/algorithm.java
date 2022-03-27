package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/27
 */
public class Problem_2028_FindMissingObservations {

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int totalTimes = n + m;
        int totalSum = totalTimes * mean;
        int preSum = 0;
        for (int r : rolls) {
            preSum += r;
        }
        int rest = totalSum - preSum;
        if (rest < n || rest > 6 * n) {
            return new int[0];
        }
        int[] ans = new int[n];
        int mod = rest % n, mer = rest / n;
        for (int i = 0; i < n; i++) {
            ans[i] = mer + (i < mod ? 1 : 0);
        }
        return ans;
    }
}
