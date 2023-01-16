package com.system.III_training.day33;

/**
 * @author ycb
 * @since 2021/10/20-14:10
 */
public class Problem_0213_HouseRobberII {

    public static int rob(int[] nums) {
        if (nums == null | nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int N = nums.length;
        int p1 = nums[0];
        int p2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < N - 1; i++) {
            int cur = Math.max(p2, p1 + nums[i]);
            p1 = p2;
            p2 = cur;
        }
        int ans1 = p2;
        p1 = nums[1];
        p2 = Math.max(nums[1], nums[2]);
        for (int i = 3; i < N; i++) {
            int cur = Math.max(p2, p1 + nums[i]);
            p1 = p2;
            p2 = cur;
        }
        return Math.max(ans1, p2);
    }
}
