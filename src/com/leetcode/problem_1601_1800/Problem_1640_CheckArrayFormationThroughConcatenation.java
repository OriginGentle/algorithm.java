package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/9/22-08:31
 */
public class Problem_1640_CheckArrayFormationThroughConcatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int n = arr.length, m = pieces.length;
        int[] map = new int[110];
        for (int i = 0; i < m; i++) {
            map[pieces[i][0]] = i;
        }

        for (int i = 0; i < n; ) {
            int[] cur = pieces[map[arr[i]]];
            int idx = 0, len = cur.length;
            while (idx < cur.length && cur[idx] == arr[i + idx])
                idx++;

            if (idx == len)
                i += len;
            else
                return false;
        }
        return true;
    }
}
