package com.leetcode.questions.problem_1801_2000;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/12/19-12:51
 */
public class Problem_1971_FindIfPathExistsInGraph {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        return dfs(graph, source, destination, visited);
    }

    public boolean dfs(ArrayList<ArrayList<Integer>> graph, int start, int end, boolean[] visited) {
        if (start == end)
            return true;

        visited[start] = true;
        for (int next : graph.get(start)) {
            if (!visited[next] && dfs(graph, next, end, visited))
                return true;
        }
        return false;
    }
}
