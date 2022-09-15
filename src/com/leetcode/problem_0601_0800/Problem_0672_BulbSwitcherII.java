package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/9/15-16:12
 */
public class Problem_0672_BulbSwitcherII {

    public int flipLights(int n, int presses) {
        if (presses == 0)
            return 1;

        else if (n == 1)
            return 2;

        else if (n == 2)
            return presses == 1 ? 3 : 4;

        else
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
    }
}
