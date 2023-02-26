package com.system.D_weekly.code_2023_02_4_week;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/2/23-08:25
 * @desc 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态
 * 给你一个由灯的位置组成的二维数组 lamps
 * 其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯
 * 即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格
 * 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj]
 * 对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的
 * 则查询结果为 1 ，否则为 0 。在第 j 次查询之后 [按照查询的顺序]
 * 关闭 位于单元格 grid[rowj][colj] 上
 * 及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果
 * 1 表示照亮，0 表示未照亮
 * 测试链接 : https://leetcode.cn/problems/grid-illumination/
 */
public class Code03_GridIllumination {

    public static int[][] move = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        long limit = n;
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> leftUpDiag = new HashMap<>();
        Map<Integer, Integer> rightUpDiag = new HashMap<>();
        Set<Long> points = new HashSet<>();
        for (int[] lamp : lamps) {
            if (points.add(lamp[0] * limit + lamp[1])) {
                row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
                col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
                leftUpDiag.put(lamp[0] - lamp[1], leftUpDiag.getOrDefault(lamp[0] - lamp[1], 0) + 1);
                rightUpDiag.put(lamp[0] + lamp[1], rightUpDiag.getOrDefault(lamp[0] + lamp[1], 0) + 1);
            }
        }

        int[] ans = new int[queries.length];
        int ai = 0;
        for (int[] q : queries) {
            ans[ai++] = row.containsKey(q[0]) || col.containsKey(q[1]) ||
                    leftUpDiag.containsKey(q[0] - q[1]) || rightUpDiag.containsKey(q[0] + q[1]) ? 1 : 0;

            for (int[] next : move) {
                int nr = next[0] + q[0];
                int nc = next[1] + q[1];
                int lu = nr - nc;
                int ru = nr + nc;
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                if (points.contains(nr * limit + nc)) {
                    points.remove(nr * limit + nc);
                    minusOrRemove(row, nr);
                    minusOrRemove(col, nc);
                    minusOrRemove(leftUpDiag, lu);
                    minusOrRemove(rightUpDiag, ru);
                }
            }
        }
        return ans;
    }

    public static void minusOrRemove(Map<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }
}
