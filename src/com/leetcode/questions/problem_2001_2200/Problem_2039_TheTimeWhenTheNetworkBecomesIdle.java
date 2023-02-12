package com.leetcode.questions.problem_2001_2200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ycb
 * @date 2022/3/20-19:00
 */
public class Problem_2039_TheTimeWhenTheNetworkBecomesIdle {

    public int networkBecomesIdle(int[][] edges, int[] p) {
        int n = p.length;
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nexts.add(new ArrayList<>());
        }
        // 无向图
        for (int[] e : edges) {
            nexts.get(e[0]).add(e[1]);
            nexts.get(e[1]).add(e[0]);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        int ans = 0, dis = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();
                for (Integer next : nexts.get(cur)) {
                    if (visited[next]) continue;
                    visited[next] = true;
                    queue.add(next);
                    int time = p[next] * ((dis * 2 - 1) / p[next]) + dis * 2 + 1;
                    ans = Math.max(ans, time);
                }
            }
            dis++;
        }
        return ans;
    }
}
