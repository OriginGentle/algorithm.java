package com.leetcode.questions.problem_1401_1600;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/3/20-20:38
 */
public class Problem_1502_CanMakeArithmeticProgressionFromSequence {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] << 1 != arr[i - 1] + arr[i + 1]) return false;
        }
        return true;
    }
}
