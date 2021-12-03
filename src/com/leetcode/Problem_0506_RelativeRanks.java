package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/12/2-11:39
 */
public class Problem_0506_RelativeRanks {

    public static String[] findRelativeRanks1(int[] score) {
        if (score == null || score.length == 0) {
            return null;
        }
        int N = score.length;
        String[] ans = new String[N];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (b.score - a.score));
        for (int i = 0; i < N; i++) {
            heap.add(new Node(score[i], i));
        }
        if (heap.size() <= 3) {
            int size = heap.size();
            for (int i = 0; i < size; i++) {
                Node cur = heap.poll();
                ans[cur.index] = i == 0 ? "Gold Medal" : i == 1 ? "Silver Medal" : "Bronze Medal";
            }
            return ans;
        }
        for (int i = 0; i < 3; i++) {
            Node cur = heap.poll();
            ans[cur.index] = i == 0 ? "Gold Medal" : i == 1 ? "Silver Medal" : "Bronze Medal";
        }
        int index = 4;
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            ans[cur.index] = String.valueOf(index++);
        }
        return ans;
    }

    public static class Node {
        public int score;
        public int index;

        public Node(int s, int i) {
            score = s;
            index = i;
        }
    }

    /*
    ====================================================================================================================
     */

    static String[] ss = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};

    public static String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        String[] ans = new String[n];
        int[] clone = score.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            map.put(clone[i], n - 1 - i);
        }
        for (int i = 0; i < n; i++) {
            int rank = map.get(score[i]);
            ans[i] = rank < 3 ? ss[rank] : String.valueOf(rank + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        String[] ans = findRelativeRanks2(arr);
        for (String s : ans) {
            System.out.print(s + ",");
        }
    }
}
