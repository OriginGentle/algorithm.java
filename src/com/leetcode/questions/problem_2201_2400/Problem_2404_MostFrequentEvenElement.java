package com.leetcode.questions.problem_2201_2400;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author ycb
 * @date 2023/4/13-08:53
 */
public class Problem_2404_MostFrequentEvenElement {

    public int mostFrequentEven(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        int res = -1, cnt = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (res == -1 || entry.getValue() > cnt) {
                res = entry.getKey();
                cnt = entry.getValue();
            }
        }
        return res;
    }
}
