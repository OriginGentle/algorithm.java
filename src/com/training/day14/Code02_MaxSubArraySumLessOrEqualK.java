package com.training.day14;

import java.util.TreeSet;

/**
 * @author ycb
 * @date 2021/9/8-9:11
 * @description 请返回arr中，求子数组的累加和，是<=K的并且是最大的
 * 返回这个最大的累加和
 */
public class Code02_MaxSubArraySumLessOrEqualK {

    public static int getMaxLessOrEqualK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // i之前的累积和，加入有序表
        TreeSet<Integer> set = new TreeSet<>();
        // 一个数都没有的时候，累加和就是0
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // 求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 找最接近 sum-k 的数
            if (set.ceiling(sum - k) != null) {
                max = Math.max(max, sum - set.ceiling(sum - k));
            }
            set.add(sum);
        }
        return max;
    }

}
