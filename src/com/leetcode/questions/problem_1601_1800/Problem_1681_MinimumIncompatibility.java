package com.leetcode.questions.problem_1601_1800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/6/28-09:12
 */
public class Problem_1681_MinimumIncompatibility {

    public static int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        // 每个子集的大小
        int group = n / k;
        int[] g = new int[1 << n];
        Arrays.fill(g, -1);
        for (int i = 1; i < (1 << n); i++) {
            if (Integer.bitCount(i) != group) {
                continue;
            }

            Set<Integer> set = new HashSet<>();
            int mi = 20, mx = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if (!set.add(nums[j])) {
                        break;
                    }

                    mi = Math.min(mi, nums[j]);
                    mx = Math.max(mx, nums[j]);
                }
            }

            if (set.size() == group) {
                g[i] = mx - mi;
            }
        }

        int[] dp = new int[1 << n];
        int inf = Integer.MAX_VALUE;
        Arrays.fill(dp, inf);

        dp[0] = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (dp[i] == inf) {
                continue;
            }

            Set<Integer> set = new HashSet<>();
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 0 && !set.contains(nums[j])) {
                    set.add(nums[j]);
                    mask |= 1 << j;
                }
            }

            if (set.size() < group) {
                continue;
            }

            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] != -1) {
                    dp[i | j] = Math.min(dp[i | j], dp[i] + g[j]);
                }
            }
        }
        return dp[(1 << n) - 1] == inf ? -1 : dp[(1 << n) - 1];
    }
}
