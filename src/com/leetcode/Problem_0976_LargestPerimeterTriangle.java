package com.leetcode;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/3/19-16:35
 */
public class Problem_0976_LargestPerimeterTriangle {

    public int largestPerimeter(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i - 1] + nums[i - 2] > nums[i]) {
                ans = nums[i] + nums[i - 1] + nums[i - 2];
                break;
            }
        }
        return ans;
    }
}
