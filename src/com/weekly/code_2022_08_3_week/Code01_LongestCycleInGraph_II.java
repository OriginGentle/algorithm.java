package com.weekly.code_2022_08_3_week;

/**
 * @author ycb
 * @date 2022/8/19-14:30
 * @desc https://leetcode.cn/problems/longest-cycle-in-a-graph/
 * 给你一个 n个节点的 有向图，节点编号为0到n - 1，
 * 其中每个节点至多有一条出边。
 * 图用一个大小为n下标从0开始的数组edges表示，
 * 节点i到节点edges[i]之间有一条有向边。
 * 如果节点i没有出边，那么edges[i] == -1。
 * 请你返回图中的 最长环，如果没有任何环，请返回 -1。
 * 一个环指的是起点和终点是 同一个节点的路径。
 */
public class Code01_LongestCycleInGraph_II {

    public static int longestCycle(int[] edges) {
        int n = edges.length;
        int[] ids = new int[n];
        int ans = -1;
        for (int from = 0, cnt = 1; from < n; from++) {
            if (ids[from] == 0) {
                for (int cur = from, fromId = cnt; cur != -1; cur = edges[cur]) {
                    // cur -> -> ->
                    if (ids[cur] > 0) {
                        // 访问过，此时的环
                        if (ids[cur] >= fromId) {
                            ans = Math.max(ans, cnt - ids[cur]);
                        }
                        break;
                    }
                    ids[cur] = cnt++;
                }
            }
        }
        return ans;
    }
}
