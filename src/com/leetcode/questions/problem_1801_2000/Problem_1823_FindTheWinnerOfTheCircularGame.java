package com.leetcode.questions.problem_1801_2000;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ycb
 * @date 2022/5/4-16:39
 */
public class Problem_1823_FindTheWinnerOfTheCircularGame {

    // 队列模拟
    public int findTheWinner1(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    /*
    ====================================================================================================================
     */

    public int findTheWinner2(int n, int k) {
        if (n <= 1) {
            return n;
        }
        int ans = (findTheWinner2(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }
}
