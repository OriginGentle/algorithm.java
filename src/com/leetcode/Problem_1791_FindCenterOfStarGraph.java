package com.leetcode;

/**
 * @author ycb
 * @since 2022/2/18-8:21
 */
public class Problem_1791_FindCenterOfStarGraph {

    public static int findCenter(int[][] edges) {
        int a = edges[0][0], b = edges[0][1];
        return a == edges[1][0] || a == edges[1][1] ? a : b;
    }
}
