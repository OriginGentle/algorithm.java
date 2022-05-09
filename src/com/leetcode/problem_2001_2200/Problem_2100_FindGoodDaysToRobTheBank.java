package com.leetcode.problem_2001_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/3/6-0:39
 */
public class Problem_2100_FindGoodDaysToRobTheBank {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        int n = security.length;
        if (((time << 1) + 1) > n) {
            return ans;
        }
        // i之前连续多少天是非递增的
        int[] before = new int[n];
        // i之后连续多少天是非递减的
        int[] after = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                before[i] = before[i - 1] + 1;
            }
            if (security[n - i - 1] <= security[n - i]) {
                after[n - i - 1] = after[n - i] + 1;
            }
        }
        for (int i = time; i < n - time; i++) {
            if (before[i] >= time && after[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }
}
