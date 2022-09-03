package com.leetcode.problem_0601_0800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2022/9/3-19:52
 */
public class Problem_0646_MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0)
            return 0;

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                ans++;
            }
        }
        return ans;
    }
}
