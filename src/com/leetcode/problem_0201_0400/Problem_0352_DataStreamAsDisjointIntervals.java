package com.leetcode.problem_0201_0400;

import java.util.*;

/**
 * @author ycb
 * @date 2022/11/7-14:33
 */
public class Problem_0352_DataStreamAsDisjointIntervals {

    class SummaryRanges {

        private final int[] father = new int[10001];
        private final Set<Integer> set = new TreeSet<>();

        public SummaryRanges() {
            Arrays.fill(father, -1);
        }

        public void addNum(int value) {
            if (father[value] == -1) {
                father[value] = value;
                set.add(value);
                union(value, value + 1);
                union(value - 1, value);
            }
        }

        public int[][] getIntervals() {
            List<int[]> ans = new ArrayList<>();

            for (int start : set) {
                int end = find(start);
                ans.add(new int[]{start, end});
            }
            return ans.toArray(new int[ans.size()][2]);
        }

        private int find(int x) {
            if (father[x] != x)
                father[x] = find(father[x]);

            return father[x];
        }

        private void union(int x, int y) {
            if (x >= 0 && x <= 10000 && father[x] != -1 && father[y] != -1) {
                int fx = find(x);
                int fy = find(y);
                if (fx != fy) {
                    father[fx] = fy;
                    set.remove(y);
                }
            }
        }
    }
}
