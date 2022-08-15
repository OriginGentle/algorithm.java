package com.leetcode.problem_biweekly;


public class Problem_2 {

    public static int edgeScore(int[] edges) {
        // 下标代表节点
        // 数组值代表积分
        int n = edges.length;
        long[] cmt = new long[n];
        for (int i = 0; i < edges.length; i++) {
            cmt[edges[i]] += i;
        }
        long max = 0, ans = -1;
        for (int i = 0; i < cmt.length; i++) {
            if (cmt[i] > max) {
                max = cmt[i];
                ans = i;
            } else if (cmt[i] == max) {
                ans = Math.min(ans, i);
            }
        }
        return (int) ans;
    }
}
