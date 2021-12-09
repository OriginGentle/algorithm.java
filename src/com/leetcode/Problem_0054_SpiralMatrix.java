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
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--, ans);
        }
        return ans;
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC, List<Integer> ans) {
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                ans.add(m[tR][i]);
            }
        } else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                ans.add(m[i][tC]);
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                ans.add(m[tR][curC]);
                curC++;
            }
            while (curR != dR) {
                ans.add(m[curR][dC]);
                curR++;
            }
            while (curC != tC) {
                ans.add(m[dR][curC]);
                curC--;
            }
            while (curR != tR) {
                ans.add(m[curR][tC]);
                curR--;
            }
        }
    }
}
