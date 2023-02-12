package com.leetcode.questions.problem_0801_1000;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/7/2-21:16
 */
public class Problem_0871_MinimumNumberOfRefuelingStops {

    public static int minRefuelStops(int t, int s, int[][] stations) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int n = stations.length, index = 0, ans = 0;
        int rest = s, dis = 0;

        while (dis < t) {
            // 当前剩余的油量
            if (rest == 0) {
                if (!heap.isEmpty() && ++ans >= 0)
                    rest += heap.poll();
                else
                    return -1;
            }
            dis += rest;
            rest = 0;
            while (index < n && stations[index][0] <= dis)
                heap.add(stations[index++][1]);
        }
        return ans;
    }
}
