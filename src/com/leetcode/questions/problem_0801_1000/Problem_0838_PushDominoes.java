package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @since 2022/2/21-8:28
 */
public class Problem_0838_PushDominoes {

    public static String pushDominoes(String dominoes) {
        char[] str = dominoes.toCharArray();
        int n = str.length;
        int[] help = new int[n];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (str[i] == '.') {
                continue;
            }
            int dire = str[i] == 'L' ? -1 : 1;
            deque.add(new int[]{i, 1, dire});
            help[i] = 1;
        }
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int loc = cur[0], time = cur[1], dire = cur[2];
            int ne = loc + dire;
            if (str[loc] == '.' || ne < 0 || ne >= n) {
                continue;
            }
            if (help[ne] == 0) { // 首次受力
                deque.addLast(new int[]{ne, time + 1, dire});
                help[ne] = time + 1;
                str[ne] = dire == -1 ? 'L' : 'R';
            } else if (help[ne] == time + 1) {
                str[ne] = '.';
            }
        }
        return String.valueOf(str);
    }
}
