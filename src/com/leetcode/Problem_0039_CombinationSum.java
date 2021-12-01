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
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);
        dfs2(candidates, 0, target, new LinkedList(), ans);
        return ans;
    }

    public static void dfs2(int[] arr, int index, int target, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (target - arr[i] < 0) {
                break;
            }
            path.addLast(arr[i]);
            dfs2(arr, i, target - arr[i], path, ans);
            path.pollLast();
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        int target = 8;
        List<List<Integer>> ans = combinationSum2(arr, target);
        for (int i = 0; i < ans.size(); i++) {
            List<Integer> tmp = ans.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                System.out.print(tmp.get(j) + ",");
            }
            System.out.println();
        }
    }
}
