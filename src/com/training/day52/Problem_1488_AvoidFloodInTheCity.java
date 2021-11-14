package com.training.day52;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/11/14-18:48
 */
public class Problem_1488_AvoidFloodInTheCity {

    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        int[] invalid = new int[0];
        // key : 某个湖
        // value : 哪些天在这个湖降雨
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] != 0) {
                if (!map.containsKey(rains[i])) {
                    map.put(rains[i], new LinkedList<>());
                }
                map.get(rains[i]).addLast(i);
            }
        }
        // 某个湖如果满了，加入到set里
        // 某个湖被抽干了，从set中移除
        HashSet<Integer> set = new HashSet<>();
        // 这个堆的堆顶表示最先处理的湖是哪个
        PriorityQueue<Work> head = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] != 0) {
                if (set.contains(rains[i])) {
                    return invalid;
                }
                set.add(rains[i]);
                map.get(rains[i]).pollFirst();
                if (!map.get(rains[i]).isEmpty()) {
                    head.add(new Work(rains[i], map.get(rains[i]).peekFirst()));
                }
                ans[i] = -1;
            } else {
                if (head.isEmpty()) {
                    ans[i] = 1;
                } else {
                    Work cur = head.poll();
                    set.remove(cur.lake);
                    ans[i] = cur.lake;
                }
            }
        }
        return ans;
    }

    public static class Work implements Comparable<Work> {
        public int lake;
        public int priority;

        public Work(int l, int p) {
            lake = l;
            priority = p;
        }

        @Override
        public int compareTo(Work o) {
            return priority - o.priority;
        }
    }
}
