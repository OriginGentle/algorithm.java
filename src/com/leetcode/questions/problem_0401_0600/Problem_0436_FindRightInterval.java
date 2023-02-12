package com.leetcode.questions.problem_0401_0600;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/5/20-08:29
 */
public class Problem_0436_FindRightInterval {

    public static int[] findRightInterval1(int[][] intervals) {
        int n = intervals.length;
        int[][] help = new int[n][2];
        for (int i = 0; i < n; i++) {
            help[i] = new int[]{intervals[i][0], i};
        }
        Arrays.sort(help, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;
            while (l < r) {
                int m = l + r >> 1;
                if (help[m][0] >= intervals[i][1]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            ans[i] = help[r][0] >= intervals[i][1] ? help[r][1] : -1;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int[] findRightInterval2(int[][] its) {
        int n = its.length;
        int[][] ss = new int[n][2], es = new int[n][2];
        for (int i = 0; i < n; i++) {
            ss[i] = new int[]{its[i][0], i};
            es[i] = new int[]{its[i][1], i};
        }
        Arrays.sort(ss, (a, b) -> a[0] - b[0]);
        Arrays.sort(es, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int[] cur = es[i];
            int loc = cur[0], idx = cur[1];
            while (j < n && ss[j][0] < loc) j++;
            ans[idx] = j == n ? -1 : ss[j][1];
        }
        return ans;
    }
}
