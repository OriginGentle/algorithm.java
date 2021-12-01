package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/11/30-9:59
 */
public class Problem_0039_CombinationSum {

    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cand = new LinkedList();
        dfs1(candidates, 0, target, res, cand);
        return res;
    }

    public static void dfs1(int[] arr, int index, int rest, List<List<Integer>> ans, LinkedList<Integer> cand) {
        if (index == arr.length) {
            return;
        }
        if (rest == 0) {
            ans.add(new ArrayList<>(cand));
            return;
        }
        // 不要当前位置的数
        dfs1(arr, index + 1, rest, ans, cand);
        if (rest - arr[index] >= 0) {
            cand.addLast(arr[index]);
            dfs1(arr, index, rest - arr[index], ans, cand);
            cand.pollLast();
        }
    }

    /*
    ====================================================================================================================
     */

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cand = new LinkedList();
        Arrays.sort(candidates);
        dfs2(candidates, 0, target, res, cand);
        return res;
    }

    public static void dfs2(int[] arr, int index, int rest, List<List<Integer>> ans, LinkedList<Integer> path) {
        if (rest == 0) {
            ans.add(path);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (rest - arr[index] >= 0) {
                path.addLast(arr[index]);
                dfs2(arr, index + 1, rest - arr[index], ans, path);
                path.pollLast();
            } else {
                break;
            }
        }
    }
}
