package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/18-19:30
 */
public class Problem_1764_FormArrayByConcatenatingSubArraysOfAnotherArray {

    public boolean canChoose(int[][] groups, int[] nums) {
        int p1 = 0, p2, n = nums.length;
        for (int[] g : groups) {
            p2 = 0;
            while (p2 < g.length && p1 < n) {
                if (nums[p1++] == g[p2]) {
                    p2++;
                } else {
                    p1 -= p2;
                    p2 = 0;
                }
            }
            if (p1 >= n && p2 != g.length)
                return false;
        }
        return true;
    }
}
