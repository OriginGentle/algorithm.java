package com.leetcode.problem_0401_0600;

import java.util.Random;

/**
 * @author ycb
 * @date 2022/6/9-20:18
 */
public class Problem_0497_RandomPointInNonOverlappingRectangles {

    class Solution {

        int[][] rs;
        int[] sum;
        Random random;
        int n;

        public Solution(int[][] rects) {
            rs = rects;
            n = rs.length;
            sum = new int[n + 1];
            random = new Random();
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + (rs[i - 1][2] - rs[i - 1][0] + 1) * (rs[i - 1][3] - rs[i - 1][1] + 1);
            }
        }

        public int[] pick() {
            int val = random.nextInt(sum[n]) + 1;
            int l = 0;
            int r = n;
            while (l < r) {
                int m = l + r >> 1;
                if (sum[m] >= val)
                    r = m;
                else
                    l = m + 1;
            }
            int[] cur = rs[r - 1];
            int x = random.nextInt(cur[2] - cur[0] + 1) + cur[0];
            int y = random.nextInt(cur[3] - cur[1] + 1) + cur[1];
            return new int[]{x, y};
        }
    }
}
