package com.leetcode.problem_0801_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/5/6-08:11
 */
public class Problem_0933_NumberOfRecentCalls {

    public static class RecentCounter {

        public Deque<Integer> deque;

        public RecentCounter() {
            deque = new ArrayDeque<>();
        }

        public int ping(int t) {
            deque.add(t);
            while (!deque.isEmpty() && deque.peek() < t - 3000) {
                deque.poll();
            }
            return deque.size();
        }
    }
}
