package com.leetcode.problem_biweekly;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Problem_1 {

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        TreeMap<Integer, Integer> cmt = new TreeMap<>();
        for (int[] arr : items1) {
            if (cmt.containsKey(arr[0])) {
                cmt.put(arr[0], arr[1]);
            } else {
                Integer pre = cmt.get(arr[0]);
                pre += arr[1];
                cmt.put(arr[0], pre);
            }
        }
        for (int[] arr : items2) {
            if (cmt.containsKey(arr[0])) {
                cmt.put(arr[0], arr[1]);
            } else {
                Integer pre = cmt.get(arr[0]);
                pre += arr[1];
                cmt.put(arr[0], pre);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cmt.entrySet()) {
            List<Integer> cur = new ArrayList<>();
            cur.add(entry.getKey());
            cur.add(entry.getValue());
            ans.add(cur);
        }
        return ans;
    }
}
