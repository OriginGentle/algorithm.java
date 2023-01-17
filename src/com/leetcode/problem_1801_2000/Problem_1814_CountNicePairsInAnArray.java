package com.leetcode.problem_1801_2000;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2023/1/17-09:05
 */
public class Problem_1814_CountNicePairsInAnArray {

    public int countNicePairs(int[] nums) {
        final int MOD = 1000000007;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            int tmp = i, j = 0;
            while (tmp > 0) {
                j = j * 10 + tmp % 10;
                tmp /= 10;
            }
            ans = (ans + map.getOrDefault(i - j, 0)) % MOD;
            map.put(i - j, map.getOrDefault(i - j, 0) + 1);
        }
        return ans;
    }
}
