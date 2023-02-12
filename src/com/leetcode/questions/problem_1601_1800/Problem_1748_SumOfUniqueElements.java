package com.leetcode.questions.problem_1601_1800;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/2/6-0:15
 */
public class Problem_1748_SumOfUniqueElements {

    public static int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int cur : map.keySet()) {
            if (map.get(cur) == 1) {
                ans += cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2};
        sumOfUnique(arr);
    }
}
