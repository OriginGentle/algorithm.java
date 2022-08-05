package com.weekly.code_2022_08_1_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/8/5-11:10
 * @desc https://leetcode.cn/problems/shortest-impossible-sequence-of-rolls/
 */
public class Code04_ShortestImpossibleSequenceOfRolls {

    public static int shortestSequence(int[] rolls, int k) {
        // 1~k上，某个数字是否收集到了！
        // set[i] == true
        // set[i] == false
        boolean[] set = new boolean[k + 1];
        // 当前这一套，收集了几个数字了？
        int size = 0;
        int ans = 0;
        for (int num : rolls) {
            if (!set[num]) {
                set[num] = true;
                size++;
            }
            if (size == k) {
                ans++;
                Arrays.fill(set, false);
                size = 0;
            }
        }
        return ans + 1;
    }
}
