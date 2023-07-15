package com.system.D_weekly.code_2023_07_2_week;

/**
 * @author ycb
 * @date 2023/7/13-15:05
 * @desc 一个公司准备组织一场会议，邀请名单上有 n 位员工
 * 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工
 * 每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议
 * 每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite
 * 其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目
 * 测试链接 : https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
 */
public class Code04_MaximumEmployeesToBeInvitedToAMeeting {

    public static int maximumInvitations(int[] favorite) {
        int[][] loved = beLoved(favorite);
        int[] degree = degree(loved);

        int n = favorite.length;
        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue[r++] = i;
            }
        }

        boolean[] zeroVisited = new boolean[n];
        int visited = 0;
        while (l < r) {
            int cur = queue[l++];
            zeroVisited[cur] = true;
            visited++;
            if (--degree[favorite[cur]] == 0) {
                queue[r++] = favorite[cur];
            }
        }

        if (visited == n) {
            return 0;
        }

        boolean[] cycleVisited = new boolean[n];
        int arrangeTwoCycle = 0, arrangeMoreCycle = 0;
        for (int i = 0; i < n; i++) {
            if (!zeroVisited[i] && !cycleVisited[i]) {
                cycleVisited[i] = true;

                if (favorite[favorite[i]] == i) {

                    cycleVisited[favorite[i]] = true;
                    arrangeTwoCycle += maxFollow(i, zeroVisited, loved)
                            + maxFollow(favorite[i], zeroVisited, loved);

                } else {

                    int cur = favorite[i];
                    int curAns = 1;
                    while (cur != i) {
                        cycleVisited[cur] = true;
                        curAns++;
                        cur = favorite[cur];
                    }

                    arrangeMoreCycle = Math.max(curAns, arrangeMoreCycle);

                }
            }
        }

        return Math.max(arrangeMoreCycle, arrangeTwoCycle);
    }

    // cur不在环上的追随者链条最大长度
    private static int maxFollow(int cur, boolean[] zeroed, int[][] from) {
        if (from[cur].length == 0) {
            return 1;
        }

        int ans = 0;
        for (int pre : from[cur]) {
            if (zeroed[pre]) {
                ans = Math.max(ans, maxFollow(pre, zeroed, from));
            }
        }
        return ans + 1;
    }

    // 求每个点的入度
    private static int[] degree(int[][] loved) {
        int n = loved.length;
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            // 喜欢 i 员工的人数有几个
            // 即点的入度
            degree[i] = loved[i].length;
        }
        return degree;
    }

    // favorite[i] = a
    // 表示 i 喜欢的员工是 a
    private static int[][] beLoved(int[] favorite) {
        int n = favorite.length;

        // size[i] = b
        // 喜欢 i 员工的人数有 b 个
        int[] size = new int[n];
        for (int love : favorite) {
            // 喜欢当前员工的有几个人
            size[love]++;
        }

        int[][] loved = new int[n][];
        for (int i = 0; i < n; i++) {
            loved[i] = new int[size[i]];
        }

        for (int i = 0; i < n; i++) {
            loved[favorite[i]][--size[favorite[i]]] = i;
        }

        // loved[i][j] = c
        // 喜欢 i 员工的人数集合中
        // 排在第 j 个的是 c
        return loved;
    }
}
