package com.system.IV_weekly.code_2022_11_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/11/26-19:49
 * @desc https://leetcode.cn/problems/split-array-with-same-average/
 * 给定你一个整数数组 nums
 * 我们要将 nums 数组中的每个元素移动到 A 集合 或者 B 集合中
 * 使得 A 集合和 B 集合不为空，并且 average(A) == average(B)
 * 如果可以完成则返回true，否则返回false。
 * 注意：对于数组 arr,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
 */
public class Code04_SplitArrayWithSameAverage {

    public static int n, s, l, r;
    public static int[] lValues = new int[1 << 15];
    public static int[] rValues = new int[1 << 15];

    public static boolean splitArraySameAverage(int[] nums) {
        n = nums.length;
        if (n == 1)
            return false;
        s = 0;
        for (int num : nums) {
            s += num;
        }

        l = 0;
        r = 0;
        int[] lArr = new int[n / 2];
        int[] rArr = new int[n - lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            lArr[i] = nums[i];
        }
        for (int i = lArr.length, j = 0; i < n; i++, j++) {
            rArr[j] = nums[i];
        }

        // 左侧 : 收集指标的时候，不能一个数也没有
        collect(lArr, true);
        // 右侧 : 收集指标的时候，不能所有数都用
        collect(rArr, false);
        Arrays.sort(rValues, 0, r);
        for (int i = 0; i < l; i++) {
            if (contains(-lValues[i]))
                return true;
        }
        return false;
    }

    public static void collect(int[] arr, boolean isLeft) {
        process(arr, 0, 0, 0, isLeft);
    }

    public static void process(int[] arr, int index, int sum, int num, boolean isLeft) {
        if (index == arr.length) {
            if (isLeft && num > 0)
                lValues[l++] = s * num - n * sum;

            if (!isLeft && num != arr.length)
                rValues[r++] = s * num - n * sum;
        } else {
            process(arr, index + 1, sum, num, isLeft);
            process(arr, index + 1, sum + arr[index], num + 1, isLeft);
        }
    }

    public static boolean contains(int num) {
        for (int left = 0, right = r - 1, mid = 0; left <= right; ) {
            mid = (left + right) / 2;
            if (rValues[mid] == num) {
                return true;
            } else if (rValues[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
