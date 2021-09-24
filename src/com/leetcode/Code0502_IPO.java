package com.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/9/8-11:06
 */
public class Code0502_IPO {

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 花费从小到大组织
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.capital - o2.capital;
            }
        });
        // 利润从大到小组织
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o2.profit - o1.profit;
            }
        });
        for (int i = 0; i < profits.length; i++) {
            minCostQueue.add(new Program(profits[i], capital[i]));
        }
        for (int i = 0; i < k; i++) {
            // 贪心的点：找成本小，利润高的项目做
            while (!minCostQueue.isEmpty() && minCostQueue.peek().capital <= w) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return w;
            }
            w += maxProfitQueue.poll().profit;
        }
        return w;
    }

    public static class Program {
        public int profit;
        public int capital;

        public Program(int p, int c) {
            profit = p;
            capital = c;
        }
    }
}
