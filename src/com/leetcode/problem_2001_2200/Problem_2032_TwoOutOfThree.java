package com.leetcode.problem_2001_2200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/12/29-22:06
 */
public class Problem_2032_TwoOutOfThree {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) | 1);
        }
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) | 2);
        }
        for (int num : nums3) {
            map.put(num, map.getOrDefault(num, 0) | 4);
        }
        List<Integer> ans = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if ((value & (value - 1)) != 0)
                ans.add(key);
        }
        return ans;
    }
}
