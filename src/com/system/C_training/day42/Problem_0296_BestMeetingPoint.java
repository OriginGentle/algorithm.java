package com.system.C_training.day42;

/**
 * @author ycb
 * @since 2021/11/4-11:22
 */
public class Problem_0296_BestMeetingPoint {

    public static int minTotalDistance(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[] iOnes = new int[N];
        int[] jOnes = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    iOnes[i]++;
                    jOnes[i]++;
                }
            }
        }
        int total = 0;
        int i = 0;
        int j = N - 1;
        int iRest = 0;
        int jRest = 0;
        while (i < j) {
            if (iOnes[i] + iRest <= iOnes[j] + jRest) {
                total += iOnes[i] + iRest;
                iRest += iOnes[i++];
            } else {
                total += jOnes[j] + jRest;
                jRest += jOnes[j--];
            }
        }
        i = 0;
        j = M - 1;
        iRest = 0;
        jRest = 0;
        while (i < j) {
            if (jOnes[i] + jRest <= jOnes[j] + jRest) {
                total += jOnes[i] + iRest;
                iRest += jOnes[i++];
            } else {
                total += jOnes[j] + jRest;
                jRest += jOnes[j--];
            }
        }
        return total;
    }
}
