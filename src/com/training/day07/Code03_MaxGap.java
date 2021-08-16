package com.training.day07;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/8/15-12:49
 * @description 给定一个数组arr，
 * 返回如果排序之后，相邻两数的最大差值
 * <p>
 * 要求：时间复杂度O(N)
 */
public class Code03_MaxGap {

    // 借用桶排序的思想
    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        // 只有一种数
        if (min == max) {
            return 0;
        }
        // 不知一种数,min~max一定有range,len个数据，准备len+1个桶
        boolean[] hasNum = new boolean[len + 1]; // hasNum[i] i号桶是否进来过数字
        int[] maxs = new int[len + 1]; // maxs[i] i号桶收集的所有数字的最大值
        int[] mins = new int[len + 1]; // mins[i] i号桶收集的所有数字的最小值
        int bid = 0; // 桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(nums[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        // 前一号桶的最大值，与后一号桶的最小值
        // 本质上就是排序后的相连值
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    // 如果当前的数是num，整个范围是min~max，分成了len + 1份
    // 返回num该进第几号桶
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

    /*
    ====================================================================================================================
     */


    // for test
    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
