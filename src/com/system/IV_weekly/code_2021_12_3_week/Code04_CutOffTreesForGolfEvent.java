package com.system.IV_weekly.code_2021_12_3_week;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @date 2021/12/18-14:05
 * @description https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class Code04_CutOffTreesForGolfEvent {

    public static int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();
        ArrayList<int[]> cells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = forest.get(i).get(j);
                if (val > 1) {
                    cells.add(new int[]{i, j, val});
                }
            }
        }
        cells.sort((a, b) -> a[2] - b[2]);
        int ans = 0, lastC = 0, lastR = 0;
        for (int[] cell : cells) {
            int step = bestWall(forest, lastR, lastC, cell[0], cell[1]);
            if (step == -1) {
                return step;
            }
            ans += step;
            lastR = cell[0];
            lastC = cell[1];
            forest.get(lastR).set(lastC, 1);
        }
        return ans;
    }

    // 0 1 2 3 4
    // i
    // 行 + next[i-1]
    // 列 + next[i]
    // i == 1 -> 上
    // i == 2 -> 右
    // i == 3 -> 下
    // i == 4 -> 左
    public static int[] next = {-1, 0, 1, 0, -1};

    public static int bestWall(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int n = forest.size();
        int m = forest.get(0).size();
        boolean[][] visited = new boolean[n][m];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offerFirst(new int[]{0, sr, sc});
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int step = cur[0], row = cur[1], col = cur[2];
            if (row == tr && col == tc) {
                return step;
            }
            visited[row][col] = true;
            for (int i = 1; i < 5; i++) {
                int nR = row + next[i - 1];
                int nC = col + next[i];
                if (nR >= 0 && nR < n && nC >= 0 && nC < m && !visited[nR][nC] && forest.get(nR).get(nC) > 0) {
                    int[] move = {step + 1, nR, nC};
                    // 更近的话
                    if ((i == 1 && row > tr) || (i == 2 && col < tc) || (i == 3 && row < tr) || (i == 4 && col > tc)) {
                        queue.offerFirst(move);
                    } else { // 更远的话，放到尾部！
                        queue.offerLast(move);
                    }
                }
            }
        }
        return -1;
    }
}
