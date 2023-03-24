package com.leetcode.questions.problem_1601_1800;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/3/23-16:03
 */
public class Problem_1630_ArithmeticSubArrays {

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            ans.add(check(nums, l[i], r[i]));
        }
        return ans;
    }

    private static boolean check(int[] nums, int start, int end) {
        Set<Integer> set = new HashSet<>();
        int n = end - start + 1;
        int a1 = Integer.MAX_VALUE, an = Integer.MIN_VALUE;
        for (int i = start; i <= end; ++i) {
            set.add(nums[i]);
            a1 = Math.min(a1, nums[i]);
            an = Math.max(an, nums[i]);
        }
        if ((an - a1) % (n - 1) != 0) {
            return false;
        }
        int diff = (an - a1) / (n - 1);
        for (int i = 1; i < n; ++i) {
            if (!set.contains(a1 + (i - 1) * diff)) {
                return false;
            }
        }
        return true;
    }
}
