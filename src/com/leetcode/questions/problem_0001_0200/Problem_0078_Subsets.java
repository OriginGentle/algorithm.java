package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ycb
 * @since 2022/1/28-14:20
 */
public class Problem_0078_Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>();
        process(nums, 0, ans, path);
        return ans;
    }

    private static void process(int[] nums, int index, List<List<Integer>> ans, Deque<Integer> path) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 不要当前位置的数
        process(nums, index + 1, ans, path);
        // 要当前位置的数
        path.addLast(nums[index]);
        process(nums, index + 1, ans, path);
        path.pollLast();
    }
}
