package com.leetcode.problem_1401_1600;

import java.util.HashSet;

/**
 * @author ycb
 * @date 2022/8/24-08:33
 */
public class Problem_1460_MakeTwoArraysEqualByReversingSubArrays {

    public int size = 1001;
    public int[] cnt;

    public boolean canBeEqual(int[] target, int[] arr) {
        cnt = new int[size];
        for (int num : target) {
            cnt[num]++;
        }
        for (int num : arr) {
            if (cnt[num] == 0 || --cnt[num] < 0) {
                return false;
            }
        }
        return true;
    }
}
