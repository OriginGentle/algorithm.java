package com.leetcode.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/11/28-16:32
 */
public class Problem_0458_PoorPigs {

    public int poorPigs(int n, int d, int t) {
        int k = t / d;
        return (int) Math.ceil(Math.log(n) / Math.log(k + 1) - 1e-5);
    }
}
