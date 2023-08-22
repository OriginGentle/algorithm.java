package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2023/8/22-18:04
 */
public class Problem_0849_MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int l = 0, n = seats.length;
        while (l < n && seats[l] == 0) {
            l++;
        }

        int ans = l;
        while (l < n) {
            int r = l + 1;
            while (r < n && seats[r] == 0) {
                r++;
            }

            if (r == n) {
                ans = Math.max(ans, r - l - 1);
            } else {
                ans = Math.max(ans, (r - l) / 2);
            }
            l = r;
        }
        return ans;
    }
}
