package com.leetcode.questions.problem_0801_1000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/11/14-12:55
 */
public class Problem_0905_SplitArrayWithSameAverage {

    public static boolean splitArraySameAverage(int[] nums) {
        int n = nums.length, m = n / 2, sum = 0;
        for (int num : nums)
            sum += num;

        Map<Integer, Set<Integer>> cmt = new HashMap<>();
        for (int s = 0; s < (1 << m); s++) {
            int tot = 0, cnt = 0;
            for (int i = 0; i < m; i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i];
                    cnt++;
                }
            }
            Set<Integer> set = cmt.getOrDefault(tot, new HashSet<>());
            set.add(cnt);
            cmt.put(tot, set);
        }

        for (int s = 0; s < (1 << (n - m)); s++) {
            int tot = 0, cnt = 0;
            for (int i = 0; i < n - m; i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i + m];
                    cnt++;
                }
            }

            for (int k = Math.max(1, cnt); k < n; k++) {
                if (k * sum % n != 0)
                    continue;

                int t = k * sum / n;
                if (!cmt.containsKey(t - tot))
                    continue;
                if (!cmt.get(t - tot).contains(k - cnt))
                    continue;
                return true;
            }
        }
        return false;
    }
}
