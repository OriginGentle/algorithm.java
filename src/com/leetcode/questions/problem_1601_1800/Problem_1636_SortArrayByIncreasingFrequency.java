package com.leetcode.questions.problem_1601_1800;

import java.util.*;

/**
 * @author ycb
 * @date 2022/9/19-08:28
 */
public class Problem_1636_SortArrayByIncreasingFrequency {

    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> cnt = new ArrayList<>();
        for (int num : nums) {
            cnt.add(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        cnt.sort((a, b) -> !Objects.equals(map.get(a), map.get(b)) ? map.get(a) - map.get(b) : b - a);

        for (int i = 0; i < cnt.size(); i++) {
            nums[i] = cnt.get(i);
        }
        return nums;
    }
}
