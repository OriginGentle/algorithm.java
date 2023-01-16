package com.system.III_training.day02;

/**
 * @Author ycb
 * @Date 2021/7/18-22:27
 * @Description 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序
 * 返回满足这一设定的子数组中，最短的是多长
 */
public class Code06_MinLengthForSort {

    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        // 从右往左遍历,找左边界在哪里
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        // 从左往右遍历，找右边界在哪里
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        System.out.println(getMinLength(arr));
    }

}
