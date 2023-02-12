package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/11/12-14:57
 */
public class Problem_0027_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        int size = nums.length;
        int index = 0;
        while (index < size) {
            if (nums[index] == val) {
                swap(nums, index, --size);
            } else {
                ++index;
            }
        }
        return size;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
