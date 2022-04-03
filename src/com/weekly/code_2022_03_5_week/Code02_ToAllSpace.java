package com.weekly.code_2022_03_5_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/4/3
 * @desc 来自微软
 * 在N*N的正方形棋盘中，有N*N个棋子，那么每个格子正好可以拥有一个棋子
 * 但是现在有些棋子聚集到一个格子上了，比如：
 * 2 0 3
 * 0 1 0
 * 3 0 0
 * 如上的二维数组代表，一共3*3个格子
 * 但是有些格子有2个棋子、有些有3个、有些有1个、有些没有
 * 请你用棋子移动的方式，让每个格子都有一个棋子
 * 每个棋子可以上、下、左、右移动，每移动一步算1的代价
 * 返回最小的代价
 */
public class Code02_ToAllSpace {

    public static int minDistance1(int[][] map) {
        int n = 0;
        int m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                n += Math.max(0, map[i][j] - 1);
                m += map[i][j] == 0 ? 1 : 0;
            }
        }
        if (n != m || n == 0) {
            return 0;
        }
        int[][] nodes = new int[n][2];
        int[][] space = new int[m][2];
        n = 0;
        m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 2; k <= map[i][j]; k++) {
                    nodes[n][0] = i;
                    nodes[n++][1] = j;
                }
                if (map[i][j] == 0) {
                    space[m][0] = i;
                    space[m++][1] = j;
                }
            }
        }
        return process(nodes, 0, space);
    }

    public static int process(int[][] nodes, int index, int[][] space) {
        int ans = 0;
        if (index == nodes.length) {
            for (int i = 0; i < nodes.length; i++) {
                ans += distance(nodes[i], space[i]);
            }
        } else {
            ans = Integer.MAX_VALUE;
            for (int i = index; i < nodes.length; i++) {
                swap(nodes, index, i);
                ans = Math.min(ans, process(nodes, index + 1, space));
                swap(nodes, index, i);
            }
        }
        return ans;
    }

    public static void swap(int[][] nodes, int i, int j) {
        int[] tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

    public static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    /*
    ====================================================================================================================
     */

    public static int minDistance2(int[][] map) {
        int n = 0;
        int m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                n += Math.max(0, map[i][j] - 1);
                m += map[i][j] != 0 ? 0 : 1;
            }
        }
        if (n != m || n == 0) {
            return 0;
        }
        int[][] nodes = new int[n][2];
        int[][] space = new int[m][2];
        n = 0;
        m = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 2; k <= map[i][j]; k++) {
                    nodes[n][0] = i;
                    nodes[n++][1] = j;
                }
                if (map[i][j] == 0) {
                    space[m][0] = i;
                    space[m++][1] = j;
                }
            }
        }
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -distance(nodes[i], space[j]);
            }
        }
        return -km(graph);
    }

    public static int km(int[][] graph) {
        int N = graph.length;
        // 记录公主当前匹配的王子是哪个
        int[] match = new int[N];
        // 王子的预期
        int[] lx = new int[N];
        // 公主的预期
        int[] ly = new int[N];
        // dfs过程中,碰过的点!
        boolean[] x = new boolean[N];
        boolean[] y = new boolean[N];
        // 降低的预期
        // 公主上，打一个，降低预期的值，只维持最小
        int[] slack = new int[N];
        int invalid = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            match[i] = -1;
            lx[i] = -invalid;
            for (int j = 0; j < N; j++) {
                lx[i] = Math.max(lx[i], graph[i][j]);
            }
            ly[i] = 0;
        }
        for (int from = 0; from < N; from++) {
            for (int i = 0; i < N; i++) {
                slack[i] = invalid;
            }
            Arrays.fill(x, false);
            Arrays.fill(y, false);
            // dfs():from 王子能不能不降低预期，匹配成功
            // 能，返回true
            // 不能，返回false
            while (!dfs(from, x, y, lx, ly, match, slack, graph)) {
                // 刚刚的dfs失败了
                // 需要拿到，公主的slack里面，预期下降幅度的最小值
                int d = invalid;
                for (int i = 0; i < N; i++) {
                    if (!y[i] && slack[i] < d) {
                        d = slack[i];
                    }
                }
                // 按照最小预期来调整当前预期
                for (int i = 0; i < N; i++) {
                    if (x[i]) {
                        lx[i] -= d;
                    }
                    if (y[i]) {
                        ly[i] += d;
                    }
                }
                Arrays.fill(x, false);
                Arrays.fill(y, false);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (lx[i] + ly[i]);
        }
        return ans;
    }

    // from, 当前的王子
    // x，王子碰没碰过
    // y, 公主碰没碰过
    // lx，所有王子的预期
    // ly, 所有公主的预期
    // match，所有公主，之前的分配，之前的爷们！
    // slack，连过，但没允许的公主，最小下降的幅度
    // map，报价，所有王子对公主的报价
    // 返回，from号王子，不降预期能不能配成！
    private static boolean dfs(int from, boolean[] x, boolean[] y, int[] lx, int[] ly,
                               int[] match, int[] slack, int[][] map) {
        int N = map.length;
        x[from] = true;
        for (int to = 0; to < N; to++) {
            if (!y[to]) { // 只有没有dfs过的公主，才去尝试，重要优化
                int d = lx[from] + ly[to] - map[from][to];
                if (d != 0) { // 如果当前的路不符合预期，更新公主的slack值
                    slack[to] = Math.min(slack[to], d);
                } else { // 如果当前的路符合预期，尝试直接拿下，或者抢夺，让直接的安排折腾去
                    y[to] = true;
                    if (match[to] == -1 || dfs(match[to], x, y, lx, ly, match, slack, map)) {
                        match[to] = from;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // for test
    public static int[][] randomValidMatrix(int len) {
        int[][] ans = new int[len][len];
        int all = len * len;
        for (int i = 1; i <= all; i++) {
            ans[(int) (Math.random() * len)][(int) (Math.random() * len)]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 4;
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[][] map = randomValidMatrix(len);
            int ans1 = minDistance1(map);
            int ans2 = minDistance2(map);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
