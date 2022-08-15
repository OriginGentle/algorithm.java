package com.leetcode.problem_biweekly;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem_3 {

    // 数字1-9,每个数字只能使用一次
    // I : arr[i] < arr[i + 1]
    // D : arr[i] > arr[i + 1]
    public static String smallestNumber(String p) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = p.length() + 1;
        int nc = 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            if (p.charAt(i) == 'I') {
                graph.get(nc).add(nc + 1);
                inDegree[nc + 1]++;
            } else {
                graph.get(nc + 1).add(nc);
                inDegree[nc]++;
            }
            nc++;
        }
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0)
                heap.add(i);
        }
        char[] ans = new char[n];
        char cur = '1';
        while (!heap.isEmpty()) {
            int c = heap.poll();
            ans[c - 1] = cur++;
            for (int next : graph.get(c)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    heap.add(next);
                }
            }
        }
        return new String(ans);
    }

    public static void main(String[] args) {
        String p = "IIDDIDDD";
        String ans = smallestNumber(p);
        System.out.println(ans);
    }
}
