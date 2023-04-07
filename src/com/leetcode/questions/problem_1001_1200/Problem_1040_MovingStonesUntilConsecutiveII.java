package com.leetcode.questions.problem_1001_1200;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/4/7-11:39
 */
public class Problem_1040_MovingStonesUntilConsecutiveII {

    public static int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int e1 = stones[n - 2] - stones[0] - n + 2;
        int e2 = stones[n - 1] - stones[1] - n + 2;
        int maxMoves = Math.max(e1, e2);
        if (e1 == 0 || e2 == 0) {
            return new int[]{Math.min(2, maxMoves), maxMoves};
        }

        int maxCnt = 0, left = 0;
        for (int right = 0; right < n; right++) {
            while (stones[right] - stones[left] + 1 > n) // 窗口大小大于 n
                ++left;
            maxCnt = Math.max(maxCnt, right - left + 1);
        }

        return new int[]{n - maxCnt, maxMoves};
    }
}
