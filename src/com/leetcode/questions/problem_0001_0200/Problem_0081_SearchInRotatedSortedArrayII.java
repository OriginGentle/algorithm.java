package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/2/17-16:26
 */
public class Problem_0081_SearchInRotatedSortedArrayII {

    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;

        }
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target == nums[mid]) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
