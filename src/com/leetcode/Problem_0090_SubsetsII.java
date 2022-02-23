package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/2/23-8:40
 */
public class Problem_0090_SubsetsII {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, 0, false, path);
        return ans;
    }

    private void dfs(int[] nums, int index, boolean choosePre, LinkedList<Integer> path) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 不要当前的数
        dfs(nums, index + 1, false, path);
        if (!choosePre && index > 0 && nums[index - 1] == nums[index]) {
            return;
        }
        path.addLast(nums[index]);
        dfs(nums, index + 1, true, path);
        path.pollLast();
    }
}
