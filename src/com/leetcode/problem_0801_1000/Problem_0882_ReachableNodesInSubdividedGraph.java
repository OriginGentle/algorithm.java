package com.leetcode.problem_0801_1000;

import java.util.*;

/**
 * @author ycb
 * @date 2022/11/26-02:09
 */
public class Problem_0882_ReachableNodesInSubdividedGraph {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], nodes = edge[2];
            graph.get(from).add(new int[]{to, nodes});
            graph.get(to).add(new int[]{from, nodes});
        }

        int ans = 0;
        HashMap<Integer, Integer> used = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.add(new int[]{0, 0});

        while (!heap.isEmpty() && heap.peek()[0] <= maxMoves) {
            int[] pair = heap.poll();
            int step = pair[0], u = pair[1];
            if (!visited.add(u)) {
                continue;
            }

            ans++;
            for (int[] next : graph.get(u)) {
                int v = next[0], nodes = next[1];
                if (nodes + step + 1 <= maxMoves && !visited.contains(v)) {
                    heap.add(new int[]{nodes + step + 1, v});
                }
                used.put(encode(u, v, n), Math.min(nodes, maxMoves - step));
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            ans += Math.min(nodes,
                    used.getOrDefault(encode(u, v, n), 0) +
                            used.getOrDefault(encode(v, u, n), 0));
        }
        return ans;
    }

    private int encode(int u, int v, int n) {
        return u * n + v;
    }
}
