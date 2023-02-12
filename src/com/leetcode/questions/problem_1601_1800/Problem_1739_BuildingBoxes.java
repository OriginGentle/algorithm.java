package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/25-22:25
 */
public class Problem_1739_BuildingBoxes {

    public static int minimumBoxes1(int n) {
        int ans = 0, maxN = 0;
        for (int i = 1; maxN + ans + i <= n; i++) {
            ans += i;
            maxN += ans;
        }

        for (int j = 1; maxN < n; j++) {
            ++ans;
            maxN += j;
        }

        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minimumBoxes2(int n) {
        int x = (int) Math.cbrt(6L * n);
        int ans = x * (x + 1) / 2;
        int maxN = (int) ((long) x * (x + 1) * (x + 2) / 6);
        if (maxN > n) {
            maxN -= ans;
            ans -= x;
        }

        double sqrt = Math.sqrt(1 + 8 * (n - maxN));
        return ans + (int) Math.ceil((-1 + sqrt) / 2);
    }
}
