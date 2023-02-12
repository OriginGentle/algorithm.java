package com.system.D_weekly.code_2022_12_4_week;

/**
 * @author ycb
 * @date 2022/12/31-20:32
 * @desc Floyd算法应用
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号
 * 给你一个数组 graph 表示这个图
 * 其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成
 * 返回能够访问所有节点的最短路径的长度
 * 你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边
 * 测试链接 : https://leetcode.cn/problems/shortest-path-visiting-all-nodes/
 */
public class Code03_ShortestPathVisitingAllNodes {

    public static int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int[][] distance = distance(n, graph);
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, process(1 << i, i, n, distance, dp));
        }
        return ans;
    }

    private static int process(int status, int cur, int n, int[][] distance, int[][] dp) {
        if (status == (1 << n) - 1)
            return 0;
        if (dp[status][cur] != -1)
            return dp[status][cur];
        int ans = Integer.MAX_VALUE;
        for (int next = 0; next < n; next++) {
            if ((status & (1 << next)) == 0 &&
                    distance[cur][next] != Integer.MAX_VALUE) {
                int nextAns = process(status | (1 << next), next, n, distance, dp);
                if (nextAns != Integer.MAX_VALUE) {
                    ans = Math.min(ans, distance[cur][next] + nextAns);
                }
            }
        }
        dp[status][cur] = ans;
        return ans;
    }

    public static int[][] distance(int n, int[][] graph) {
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            distance[i][i] = 0;
        }

        for (int cur = 0; cur < n; cur++) {
            for (int next : graph[cur]) {
                distance[next][cur] = 1;
                distance[cur][next] = 1;
            }
        }

        for (int jump = 0; jump < n; jump++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    if (distance[from][jump] != Integer.MAX_VALUE &&
                            distance[jump][to] != Integer.MAX_VALUE &&
                            distance[from][to] > distance[from][jump] + distance[jump][to]) {
                        distance[from][to] = distance[from][jump] + distance[jump][to];
                    }
                }
            }
        }
        return distance;
    }
}
