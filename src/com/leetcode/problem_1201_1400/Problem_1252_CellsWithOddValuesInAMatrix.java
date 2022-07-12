package com.leetcode.problem_1201_1400;

/**
 * @author ycb
 * @date 2022/7/12-10:28
 */
public class Problem_1252_CellsWithOddValuesInAMatrix {

    public static int oddCells1(int m, int n, int[][] indices) {
        int[][] cnt = new int[m][n];
        for (int[] arr : indices) {
            int row = arr[0];
            for (int j = 0; j < n; j++) {
                cnt[row][j]++;
            }
            int col = arr[1];
            for (int i = 0; i < m; i++) {
                cnt[i][col]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] % 2 != 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int oddCells2(int m, int n, int[][] indices) {
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        int a = 0, b = 0;
        for (int[] arr : indices) {
            a += (row[arr[0]] = !row[arr[0]]) ? 1 : -1;
            b += (col[arr[1]] = !col[arr[1]]) ? 1 : -1;
        }
        return a * (n - b) + b * (m - a);
    }

    /*
    ====================================================================================================================
     */

    public static int oddCells3(int m, int n, int[][] indices) {
        long row = 0, col = 0;
        for (int[] arr : indices) {
            row ^= 1L << arr[0];
            col ^= 1L << arr[1];
        }
        int a = 0, b = 0;
        for (int i = 0; i < m; i++)
            a += (row >> i) & 1;
        for (int i = 0; i < n; i++)
            b += (col >> i) & 1;
        return a * (n - b) + b * (m - a);
    }
}
