package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/12/1-9:08
 */
public class Problem_0040_CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        dfs(candidates, 0, target, ans, new LinkedList<>());
        return ans;
    }

    public static void dfs(int[] arr, int index, int rest, List<List<Integer>> ans, LinkedList<Integer> path) {
        if (rest == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > rest) {
                break;
            }
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            path.addLast(arr[i]);
            dfs(arr, i + 1, rest - arr[i], ans, path);
            path.pollLast();
        }
    }
}
