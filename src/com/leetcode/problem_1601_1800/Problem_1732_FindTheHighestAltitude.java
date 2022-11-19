package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/19-23:42
 */
public class Problem_1732_FindTheHighestAltitude {

    public int largestAltitude(int[] gain) {
        int ans = 0, sum = 0;
        for (int num : gain) {
            sum += num;
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
