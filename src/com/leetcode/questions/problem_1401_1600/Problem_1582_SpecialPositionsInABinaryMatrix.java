package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/9/4-15:05
 */
public class Problem_1582_SpecialPositionsInABinaryMatrix {

    public static int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] rowC = new int[n];
        int[] colC = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowC[i] += mat[i][j];
                colC[j] += mat[i][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 && rowC[i] == 1 && colC[j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
