package com.leetcode.problem_finger_offer;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/7/23-19:39
 */
public class Problem_115_ur2n8P {

    public static boolean sequenceReconstruction(int[] nums, int[][] seq) {
        int n = nums.length;
        int[] inDegrees = new int[n + 1];
        Set<Integer>[] graph = new HashSet[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] se : seq) {
            int len = se.length;
            for (int i = 1; i < len; i++) {
                int prev = se[i - 1];
                int next = se[i];
                if (graph[prev].add(next)) {
                    inDegrees[next]++;
                }
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (--inDegrees[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return true;
    }
}
