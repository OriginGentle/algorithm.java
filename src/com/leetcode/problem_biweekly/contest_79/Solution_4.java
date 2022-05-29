package com.leetcode.problem_biweekly.contest_79;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ycb
 * @date 2022/5/28-22:16
 * @desc
 */
public class Solution_4 {

    public static class BookMyShow {

        public int row;
        public int col;
        public HashMap<Integer, Integer> rowInfo;

        public BookMyShow(int n, int m) {
            row = n;
            col = m;
            rowInfo = new HashMap<>();
            for (int i = 0; i < row; i++) {
                rowInfo.put(i, 0);
            }
        }

        public int[] gather(int k, int maxRow) {
            for (int i = 0; i <= maxRow; i++) {
                int start = rowInfo.get(i);
                // 1 1 1 1 1 1 1
                // 0 1 2 3 4 5 6
                // 0 0 0
                if (start + k - 1 < col) {
                    int[] ans = new int[]{i, start};
                    rowInfo.put(i, start + k);
                    return ans;
                }
            }
            return new int[]{};
        }

        public boolean scatter(int k, int maxRow) {
            for (int i = 0; i <= maxRow; i++) {
                Integer start = rowInfo.get(i);
                // 1 1 1 1 1 1 1
                // 0 1 2 3 4 5 6
                // 1 1 1
                int can = col - start + 1;
                if (can <= 0) {
                    continue;
                }
                k -= can;
                rowInfo.put(i, start + k);
                if (k <= 0) {
                    return true;
                }
            }
            return false;
        }
    }
}
