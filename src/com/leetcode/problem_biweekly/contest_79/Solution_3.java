package com.leetcode.problem_biweekly.contest_79;

import java.util.*;

/**
 * @author ycb
 * @date 2022/5/28-22:16
 * @desc
 */
public class Solution_3 {

    public static long maximumImportance(int n, int[][] roads) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            int form = road[0];
            int to = road[1];
            map.get(form).add(to);
            map.get(to).add(form);
        }
        PriorityQueue<CityInfo> head = new PriorityQueue<>((a, b) -> a.count - b.count);
        for (int num : map.keySet()) {
            int count = map.get(num).size();
            CityInfo cityInfo = new CityInfo(num, count);
            head.add(cityInfo);
        }
        long[] info = new long[n];
        for (int i = 1; i <= n; i++) {
            CityInfo cur = head.poll();
            info[cur.number] = i;
        }
        long ans = 0;
        for (int[] road : roads) {
            int form = road[0];
            int to = road[1];
            ans += (info[form] + info[to]);
        }
        return ans;
    }

    public static class CityInfo {
        public int number;
        public int count;

        public CityInfo(int n, int c) {
            number = n;
            count = c;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
        long ans = maximumImportance(n, roads);
        System.out.println(ans);
    }
}
