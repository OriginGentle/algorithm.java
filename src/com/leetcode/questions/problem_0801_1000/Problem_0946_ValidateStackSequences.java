package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/8/31-09:30
 */
public class Problem_0946_ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            deque.addLast(pushed[i]);
            while (!deque.isEmpty() && deque.peekLast() == popped[j]) {
                deque.pollLast();
                j++;
            }
        }
        return deque.isEmpty();
    }
}
