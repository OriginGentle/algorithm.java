package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/11/4-08:33
 */
public class Problem_0754_ReachANumber {

    public int reachNumber(int target) {
        if (target == 0)
            return 0;

        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }
}
