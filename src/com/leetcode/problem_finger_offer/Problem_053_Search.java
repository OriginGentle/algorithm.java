package com.leetcode.problem_finger_offer;

/**
 * @Author ycb
 * @Date 2021/7/16-8:39
 */
public class Problem_053_Search {

    // 遍历一遍数组
    public int search1(int[] nums, int target) {
        int ans = 0;
        for (int num : nums) {
            if ((num ^ target) == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 二分法
    public int search2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int L = 4;
        int R = 8;
        int ans = L + ((R - L) >> 1);
        System.out.println(ans);
        System.out.println((R + L) / 2);
    }

}
