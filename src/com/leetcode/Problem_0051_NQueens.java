package com.leetcode;

import java.util.*;

/**
 * @author ycb
 * @since 2021/12/3-13:54
 */
public class Problem_0051_NQueens {

    public static List<List<String>> solveNQueens1(int n) {
        List<List<String>> ans = new ArrayList<>();
        if (n < 1) {
            return ans;
        }
        int[] records = new int[n];
        Arrays.fill(records, -1);
        Set<Integer> col = new HashSet<>();
        Set<Integer> dia1 = new HashSet<>();
        Set<Integer> dia2 = new HashSet<>();
        process1(ans, records, n, 0, col, dia1, dia2);
        return ans;
    }

    public static void process1(List<List<String>> ans, int[] records, int n, int row, Set<Integer> col,
                                Set<Integer> dia1, Set<Integer> dia2) {
        if (row == n) {
            List<String> pre = generatePath(records, n);
            ans.add(pre);
        } else {
            for (int i = 0; i < n; i++) {
                if (col.contains(i)) {
                    continue;
                }
                int d1 = row - i;
                if (dia1.contains(d1)) {
                    continue;
                }
                int d2 = row + i;
                if (dia2.contains(d2)) {
                    continue;
                }
                // 选择放在就i列
                records[row] = i;
                col.add(i);
                dia1.add(d1);
                dia2.add(d2);
                process1(ans, records, n, row + 1, col, dia1, dia2);
                records[row] = -1;
                col.remove(i);
                dia1.remove(d1);
                dia2.remove(d2);
            }
        }
    }

    public static List<String> generatePath(int[] records, int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] tmp = new char[n];
            Arrays.fill(tmp, '.');
            tmp[records[i]] = 'Q';
            res.add(new String(tmp));
        }
        return res;
    }

    /*
    ====================================================================================================================
     */


    public static List<List<String>> solveNQueens2(int n) {
        int[] records = new int[n];
        Arrays.fill(records, -1);
        List<List<String>> ans = new ArrayList<>();
        process2(ans, records, n, 0, 0, 0, 0);
        return ans;
    }

    public static void process2(List<List<String>> ans, int[] records, int n, int row, int col, int d1, int d2) {
        if (row == n) {
            List<String> pre = generatePath(records, n);
            ans.add(pre);
        } else {
            int availablePositions = ((1 << n) - 1) & (~(col | d1 | d2));
            while (availablePositions != 0) {
                int pos = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                int column = Integer.bitCount(pos - 1);
                records[row] = column;
                process2(ans, records, n, row + 1, col | pos, (d1 | pos) << 1, (d2 | pos) >> 1);
                records[row] = -1;
            }
        }
    }

    public static void main(String[] args) {
        int n = 9;
        solveNQueens1(n);
    }
}
