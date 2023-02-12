package com.leetcode.questions.problem_2001_2200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/2/10-18:28
 */
public class Problem_2006_CountNumberOfPairsWithAbsoluteDifferenceK {

    public static int countKDifference1(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int countKDifference2(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            ans += map.getOrDefault(nums[i] - k, 0)
                    + map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int countKDifference3(int[] nums, int k) {
        int ans = 0;
        int[] count = new int[110];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            if (t - k >= 1) {
                ans += count[t - k];
            }
            if (t + k <= 100) {
                ans += count[t + k];
            }
            count[t]++;
        }
        return ans;
    }
}
