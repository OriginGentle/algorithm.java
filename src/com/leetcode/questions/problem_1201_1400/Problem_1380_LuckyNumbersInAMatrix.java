package com.leetcode.questions.problem_1201_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/2/15-18:44
 */
public class Problem_1380_LuckyNumbersInAMatrix {

    public static List<Integer> luckyNumbers(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] row = new int[n];
        int[] col = new int[m];
        for (int i = 0; i < n; i++) {
            row[i] = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                row[i] = Math.min(row[i], matrix[i][j]);
                col[j] = Math.max(col[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cur = matrix[i][j];
                if (cur == row[i] && cur == col[j]) {
                    ans.add(cur);
                }
            }
        }
        return ans;
    }
}
