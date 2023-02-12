package com.leetcode.competition.biweekly._86;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/9/1-15:23
 */
public class Problem_1 {

    public static boolean findSubarrays(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        int n = nums.length;
        Set<Integer> cnt = new HashSet<>();
        for (int i = 1; i < n; i++) {

            int sum = nums[i] + nums[i - 1];
            if (cnt.contains(sum)) {
                return true;
            }
            cnt.add(sum);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 1};
        boolean ans = findSubarrays(nums);
        System.out.println(ans);
    }
}
