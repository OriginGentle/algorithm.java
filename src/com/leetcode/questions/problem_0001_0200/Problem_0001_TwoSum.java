package com.leetcode.questions.problem_0001_0200;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/3/11-17:45
 */
public class Problem_0001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                index[0] = i;
                index[1] = hash.get(nums[i]);
                return index;
            }
            hash.put(target - nums[i], i);
        }
        return index;
    }
}
