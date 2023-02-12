package com.system.C_training.day32;

/**
 * @author ycb
 * @since 2021/10/19-8:17
 */
public class Problem_0152_MaximumProductSubArray {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preMax = nums[0];
        int preMin = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(nums[i], Math.max(preMax * nums[i], preMin * nums[i]));
            int curMin = Math.min(nums[i], Math.min(preMax * nums[i], preMin * nums[i]));
            preMax = curMax;
            preMin = curMin;
            ans = Math.max(ans, curMax);
        }
        return ans;
    }
}
