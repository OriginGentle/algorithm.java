package com.leetcode.questions.problem_1801_2000;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/9/3-11:51
 */
public class Problem_1921_EliminateMaximumNumberOfMonsters {

    public static int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (dist[i] - 1) / speed[i] + 1;
        }

        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (arr[i] <= i) {
                return i;
            }
        }

        return n;
    }
}
