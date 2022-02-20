package com.weekly.code_2022_02_3_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/2/19-21:12
 * @description https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class Code01_CheapestFlightsWithinKStops {

    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<int[]>> grath = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            grath.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            grath.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        Map<Integer, Integer> curMap = new HashMap<>();
        curMap.put(src, 0);
        for (int i = 0; i <= k; i++) {
            Map<Integer, Integer> nextMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> cur : curMap.entrySet()) {
                int form = cur.getKey();
                int preCost = cur.getValue();
                for (int[] line : grath.get(form)) {
                    int to = line[0];
                    int curCost = line[1];
                    cost[to] = Math.min(cost[to], preCost + curCost);
                    nextMap.put(to, Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE), preCost + curCost));
                }
            }
            curMap = nextMap;
        }

        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    /*
    ====================================================================================================================
     */

    // Bellman Ford
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(cost, n);
            for (int[] f : flights) {
                // 当前点可达
                if (cost[f[0]] != Integer.MAX_VALUE) {
                    // 去下一个点的需要花费的代价
                    next[f[1]] = Math.min(next[f[1]], cost[f[0]] + f[2]);
                }
            }
            cost = next;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}
