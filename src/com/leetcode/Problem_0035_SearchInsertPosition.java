package com.leetcode;

/**
 * @author ycb
 * @since 2021/11/26-16:22
 */
public class Problem_0035_SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return nums[mid] > target ? mid : mid + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        int target = 7;
        int ans = searchInsert(arr, target);
        System.out.println(ans);
    }
}
