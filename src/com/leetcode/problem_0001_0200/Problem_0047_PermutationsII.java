package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @since 2021/12/7-11:10
 */
public class Problem_0047_PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        process(nums, 0, visited, new ArrayList<>(), ans);
        return ans;
    }

    public static void process(int[] arr, int index, boolean[] visited, ArrayList<Integer> pre, List<List<Integer>> ans) {
        if (index == arr.length) {
            ans.add(new ArrayList<>(pre));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i] || (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            pre.add(arr[i]);
            process(arr, index + 1, visited, pre, ans);
            visited[i] = false;
            pre.remove(pre.size() - 1);
        }
    }
}
