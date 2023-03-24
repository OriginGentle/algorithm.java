package com.leetcode.questions.problem_1601_1800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2023/3/15-11:26
 */
public class Problem_1615_MaximalNetworkRank {

    public static int maximalNetworkRank1(int n, int[][] roads) {
        boolean[][] conn = new boolean[n][n];
        int[] inDegree = new int[n];
        for (int[] road : roads) {
            conn[road[0]][road[1]] = true;
            conn[road[1]][road[0]] = true;
            inDegree[road[0]]++;
            inDegree[road[1]]++;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cnt = inDegree[i] + inDegree[j] - (conn[i][j] ? 1 : 0);
                max = Math.max(cnt, max);
            }
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    public static int maximalNetworkRank2(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0], y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }

        int first = -1, second = -2;
        List<Integer> firstArr = new ArrayList<>();
        List<Integer> secondArr = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (degree[i] > first) {
                second = first;
                secondArr = new ArrayList<>(firstArr);
                first = degree[i];
                firstArr.clear();
                firstArr.add(i);
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second) {
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        if (firstArr.size() == 1) {
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }

}
