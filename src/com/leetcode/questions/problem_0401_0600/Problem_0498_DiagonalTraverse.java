package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/6/14-08:05
 */
public class Problem_0498_DiagonalTraverse {

    public static int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] ans = new int[n * m];
        boolean flag = true;
        int x = 0, y = 0, i = 0;
        while (i != n * m) {
            ans[i++] = mat[x][y];
            if (flag) {
                if (x - 1 >= 0 && y + 1 < m) {
                    x--;
                    y++;
                } else {
                    if (y == m - 1) x++;
                    else y++;
                    flag = false;
                }
            } else {
                if (x + 1 < n && y - 1 >= 0) {
                    x++;
                    y--;
                } else {
                    if (x == n - 1) y++;
                    else x++;
                    flag = true;
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 10}
        };
        int[] ans = findDiagonalOrder(mat);
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
