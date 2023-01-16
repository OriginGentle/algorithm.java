package com.system.III_training.day33;

/**
 * @author ycb
 * @since 2021/10/21-8:32
 */
public class Problem_0238_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i] = nums[i] * dp[i - 1];
        }
        int right = 1;
        for (int i = N - 1; i > 0; i--) {
            dp[i] = dp[i - 1] * right;
            right *= nums[i];
        }
        dp[0] = right;
        return dp;
    }

    /*
    ====================================================================================================================
     */

    // 扩展 : 如果仅仅是不能用除号，把结果直接填在nums里呢？
    // 解法：数一共几个0；每一个位得到结果就是，a / b，位运算替代 /
}
