package com.system.C_training.day28;

/**
 * @author ycb
 * @since 2021/10/14-8:35
 */
public class Problem_0034_FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int L = lessMostRight(nums, target) + 1;
        if (L == nums.length || nums[L] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{L, lessMostRight(nums, target + 1)};
    }

    public static int lessMostRight(int[] arr, int nums) {
        int L = 0;
        int R = arr.length - 1;
        int M = 0;
        int ans = -1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (arr[M] < nums) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 9, 10};
        int target = 5;
        int[] range = searchRange(arr, target);
        for (int i : range) {
            System.out.println(i);
        }
    }
}
