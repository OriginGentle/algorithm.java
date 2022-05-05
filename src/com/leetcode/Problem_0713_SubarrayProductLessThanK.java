package com.leetcode;

/**
 * @author ycb
 * @date 2022/5/5-07:59
 */
public class Problem_0713_SubarrayProductLessThanK {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, pre = 1;
        int left = 0, right = 0;
        while (right < n) {
            pre *= nums[right++];
            while (left < right && pre >= k) {
                pre /= nums[left++];
            }
            ans += right - left;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        int ans = numSubarrayProductLessThanK(nums, k);
        System.out.println(ans);
    }
}
