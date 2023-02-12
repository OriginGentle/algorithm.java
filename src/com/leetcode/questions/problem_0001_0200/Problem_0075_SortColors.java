package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/1/18-13:32
 */
public class Problem_0075_SortColors {

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0, right = nums.length - 1;
        int index = 0;
        // 要求按照 红 白 蓝依次排序
        while (index <= right) {
            if (nums[index] == 0) { // 红色
                swap(nums, index++, left++);
            } else if (nums[index] == 1) { // 白色
                index++;
            } else { // 蓝色
                swap(nums, index, right--);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
