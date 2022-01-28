package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/28-14:29
 */
public class Problem_0080_RemoveDuplicatesFromSortedArrayII {

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 1, 3, 3, 3, 4, 4, 5, 7, 8};
        int ans = removeDuplicates(nums);
        System.out.println(ans);
    }
}
