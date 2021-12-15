package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/12/8-11:15
 */
public class Problem_0054_SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int tRow = 0;
        int tCol = 0;
        int dRow = matrix.length - 1;
        int dCol = matrix[0].length - 1;
        while (tRow <= dRow && tCol <= dCol) {
            printEdge(matrix, tRow++, tCol++, dRow--, dCol--, ans);
        }
        return ans;
    }

    public static void printEdge(int[][] m, int tRow, int tCol, int dRow, int dCol, List<Integer> ans) {
        if (tRow == dRow) {
            for (int i = tCol; i <= dCol; i++) {
                ans.add(m[tRow][i]);
            }
        } else if (tCol == dCol) {
            for (int i = tRow; i <= dRow; i++) {
                ans.add(m[i][tCol]);
            }
        } else {
            int curC = tCol;
            int curR = tRow;
            while (curC != dCol) {
                ans.add(m[tRow][curC]);
                curC++;
            }
            while (curR != dRow) {
                ans.add(m[curR][dCol]);
                curR++;
            }
            while (curC != tCol) {
                ans.add(m[dRow][curC]);
                curC--;
            }
            while (curR != tRow) {
                ans.add(m[curR][tCol]);
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = count++;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=======================");
        List<Integer> list = spiralOrder(matrix);
        list.forEach(System.out::print);
    }
}
