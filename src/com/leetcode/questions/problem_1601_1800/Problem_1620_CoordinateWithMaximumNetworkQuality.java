package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/2-11:11
 */
public class Problem_1620_CoordinateWithMaximumNetworkQuality {

    public int[] bestCoordinate(int[][] towers, int k) {
        int[][] grid = new int[110][110];
        int x = 0, y = 0, val = 0;
        for (int[] tower : towers) {
            int a = tower[0], b = tower[1], q = tower[2];
            for (int i = Math.max(0, a - k); i <= a + k; i++) {
                for (int j = Math.max(0, b - k); j <= b + k; j++) {
                    double seq = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if (seq > k)
                        continue;

                    grid[i][j] += Math.floor(q / (1 + seq));
                    if (grid[i][j] > val) {
                        x = i;
                        y = j;
                        val = grid[i][j];
                    } else if (grid[i][j] == val
                            && (i < x || (i == x && j < y))) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x, y};
    }
}
