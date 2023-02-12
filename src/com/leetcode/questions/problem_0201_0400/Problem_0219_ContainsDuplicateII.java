package com.leetcode.questions.problem_0201_0400;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @since 2022/1/19-9:54
 */
public class Problem_0219_ContainsDuplicateII {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
