package com.system.IV_weekly.code_2022_07_1_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/7/10-16:08
 * @desc https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/
 */
public class Code02_MinimumScoreAfterRemovalsOnATree {

    public static int CNT;

    public static int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 为了方便，就认为0节点是头
        // dfn[i]:i节点的dfn序是多少
        int[] dfn = new int[n];
        // size[i]:i节点为头的整棵数有多少个节点
        int[] size = new int[n];
        // xor[i]:i节点为头的整棵树整体异或值是多少
        int[] xor = new int[n];
        CNT = 1;
        dfs(nums, graph, 0, dfn, size, xor);

        int ans = Integer.MAX_VALUE;
        int m = edges.length;
        int part1, part2, part3;
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            // 谁的dfn序大，谁就是删掉之后的树的头节点
            int c1 = dfn[a] < dfn[b] ? b : a;
            for (int j = i + 1; j < m; j++) {
                int c = edges[j][0];
                int d = edges[j][1];
                int c2 = dfn[c] < dfn[d] ? d : c;

                int pre = dfn[c1] < dfn[c2] ? c1 : c2;
                int pos = pre == c1 ? c2 : c1;

                part1 = xor[pos];
                // 判断pos为头的树，是否是pre为头的树的子树
                if (dfn[pos] < dfn[pre] + size[pre]) {
                    part2 = xor[pre] ^ xor[pos];
                    part3 = xor[0] ^ xor[pre];
                } else {
                    part2 = xor[pre];
                    part3 = xor[0] ^ part1 ^ part2;
                }
                int max = Math.max(part1, Math.max(part2, part3));
                int min = Math.min(part1, Math.min(part2, part3));
                ans = Math.min(ans, max - min);
            }
        }
        return ans;
    }

    public static void dfs(int[] nums, ArrayList<ArrayList<Integer>> graph, int cur,
                           int[] dfn, int[] size, int[] xor) {
        dfn[cur] = CNT++;
        xor[cur] = nums[cur];
        size[cur] = 1;
        for (int next : graph.get(cur)) {
            if (dfn[next] == 0) {
                dfs(nums, graph, next, dfn, size, xor);
                xor[cur] ^= xor[next];
                size[cur] += size[next];
            }
        }
    }
}
