package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/9/9-18:43
 */
public class Problem_2594_MinimumTimeToRepairCars {

    public static long repairCars(int[] ranks, int cars) {
        long l = 1L, r = (long) ranks[0] * cars * cars;
        long ans = 0L;
        while (l <= r) {
            long m = (l + r) / 2;
            if (ok(ranks, cars, m)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    private static boolean ok(int[] ranks, int cars, long m) {
        long res = 0L;
        for (int rank : ranks) {
            res += Math.sqrt(m / rank);
        }
        return res >= cars;
    }
}
