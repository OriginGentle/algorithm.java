package com.leetcode.problem_0201_0400;

import java.util.TreeSet;

/**
 * @author ycb
 * @date 2022/8/31-09:48
 */
public class Problem_0220_ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            long cur = nums[i];

            Long l = set.floor(cur);
            Long r = set.ceiling(cur);

            if ((l != null && cur - l <= t) ||
                    (r != null && r - cur <= t))
                return true;

            set.add(cur);

            if (i >= k)
                set.remove((long) nums[i - k]);
        }
        return false;
    }
}
