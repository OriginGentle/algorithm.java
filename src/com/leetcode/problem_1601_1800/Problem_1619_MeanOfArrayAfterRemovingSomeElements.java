package com.leetcode.problem_1601_1800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/14-09:58
 */
public class Problem_1619_MeanOfArrayAfterRemovingSomeElements {

    public static double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, cnt = 0;

        for (int i = n / 20; i < n - n / 2; i++)
            cnt += arr[i];

        return cnt * 1d / (n * 0.9);
    }
}
