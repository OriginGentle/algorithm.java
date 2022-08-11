package com.leetcode.problem_biweekly;

import java.util.HashMap;
import java.util.Map;

public class Problem_2 {

    // i 和 j
    // i < j
    // j - i != arr[j] - arr[i]
    // 所有数对 - 好的数对
    // j - i == arr[j] - arr[i]
    // arr[i] - i = arr[j] - j
    // arr[i] - i != arr[j] - j
    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 1);
        int n = nums.length;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            int t = nums[i] - i;
            Integer cnt = map.getOrDefault(t, 0);
            ans += i - cnt;
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return ans;
    }
}
