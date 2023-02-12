package com.system.C_training.day10;

/**
 * @author ycb
 * @date 2021/8/29-13:20
 * @description https://leetcode.com/problems/jump-game-ii/
 */
public class Code01_JumpGame {

    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}
