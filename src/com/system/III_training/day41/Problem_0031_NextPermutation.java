package com.system.III_training.day41;

/**
 * @author ycb
 * @since 2021/11/1-10:12
 */
public class Problem_0031_NextPermutation {

    public static void nextPermutation(int[] nums) {
        int N = nums.length;
        int firstLess = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        if (firstLess < 0) {
            reverse(nums, 0, N - 1);
        } else {
            int rightCloseMore = -1;
            for (int i = N - 1; i >= firstLess; i--) {
                if (nums[i] > nums[firstLess]) {
                    rightCloseMore = i;
                    break;
                }
            }
            swap(nums, firstLess, rightCloseMore);
            reverse(nums, firstLess + 1, N - 1);
        }
    }

    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            swap(arr, L++, R--);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
