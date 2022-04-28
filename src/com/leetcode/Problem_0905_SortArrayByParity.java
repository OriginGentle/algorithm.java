package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/28-10:42
 */
public class Problem_0905_SortArrayByParity {

    public static int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++) {
            if (nums[i] % 2 == 1) {
                int tmp = nums[j];
                nums[j--] = nums[i];
                nums[i--] = tmp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        int[] ans = sortArrayByParity(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
