package com.system.C_training.day44;

import java.util.HashMap;

/**
 * @author ycb
 * @since 2021/11/6-17:29
 */
public class Problem_0992_SubArraysWithKDifferentIntegers {

    public static int subarraysWithKDistinct1(int[] nums, int k) {
        int n = nums.length;
        // k-1 种数的窗口词频统计
        int[] lessCounts = new int[n + 1];
        // k种数的窗口词频统计
        int[] equalCounts = new int[n + 1];
        int lessLeft = 0;
        int equalLeft = 0;
        int lessKinds = 0;
        int equalKinds = 0;
        int ans = 0;
        // 两个窗口
        for (int r = 0; r < n; r++) {
            if (lessCounts[nums[r]] == 0) {
                lessKinds++;
            }
            if (equalCounts[nums[r]] == 0) {
                equalKinds++;
            }
            lessCounts[nums[r]]++;
            equalCounts[nums[r]]++;
            while (lessKinds == k) {
                if (lessCounts[nums[lessLeft]] == 1) {
                    lessKinds--;
                }
                lessCounts[nums[lessLeft++]]--;
            }
            while (equalKinds > k) {
                if (equalCounts[nums[equalLeft]] == 1) {
                    equalKinds--;
                }
                equalCounts[nums[equalLeft++]]--;
            }
            ans += lessLeft - equalLeft;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int subarraysWithKDistinct2(int[] nums, int k) {
        return numMostK(nums, k) - numMostK(nums, k - 1);
    }

    // 最多k种数的子数组数量 - 最多k-1种数的子数组数量
    public static int numMostK(int[] arr, int k) {
        int i = 0, res = 0;
        // 词频统计表
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int j = 0; i < arr.length; j++) {
            if (count.getOrDefault(arr[j], 0) == 0) {
                k--;
            }
            count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
            while (k < 0) {
                count.put(arr[i], count.get(arr[i]) - 1);
                if (count.get(arr[i]) == 0) {
                    k++;
                }
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
