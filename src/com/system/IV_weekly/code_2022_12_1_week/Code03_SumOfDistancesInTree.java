package com.system.IV_weekly.code_2022_12_1_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/12/10-19:53
 * @desc 给定一个无向、连通的树
 * 树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
 * 给定整数 n 和数组 edges ，
 * edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
 * 返回长度为 n 的数组 answer ，其中 answer[i] :
 * 树中第 i 个节点与所有其他节点之间的距离之和。
 * https://leetcode.cn/problems/sum-of-distances-in-tree/
 */
public class Code03_SumOfDistancesInTree {

    public int N = 30001;
    public int[] size = new int[N];
    public int[] distance = new int[N];

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        collect(0, -1, graph);
        int[] ans = new int[n];
        process(0, -1, 0, graph, ans);
        return ans;
    }

    public void collect(int cur, int father, ArrayList<ArrayList<Integer>> graph) {
        size[cur] = 1;
        distance[cur] = 0;
        for (int next : graph.get(cur)) {
            if (next != father) {
                collect(next, cur, graph);
                size[cur] += size[next];
                distance[cur] += distance[next] + size[next];
            }
        }
    }

    // cur:当前节点！
    // father:当前节点的父
    // upDistance:父亲扔给cur的第二部分距离！
    // graph:图
    // ans:答案
    public void process(int cur, int father, int upDistance,
                        ArrayList<ArrayList<Integer>> graph, int[] ans) {

        ans[cur] = distance[cur] + upDistance;
        for (int next : graph.get(cur)) {
            if (next != father) {
                process(next, cur,
                        ans[cur] - distance[next] + size[0] - (size[next] << 1),
                        graph, ans);
            }
        }
    }
}
