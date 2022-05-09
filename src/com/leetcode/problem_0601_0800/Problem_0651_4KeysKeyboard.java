package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @since 2021/11/23-14:02
 */
public class Problem_0651_4KeysKeyboard {

    public static int maxA(int n) {
        int[] dp = new int[n];
        for (int i = 0; i < 6 && i < n; i++) {
            dp[i] = i + 1;
        }
        // 可以证明：
        // 来到i的时候，包括i在内最多有连续4次粘贴行为
        // 不可能更多，因为如果有连续5次粘贴，A的个数变化如下，行为一：
        // 开始    全选    复制(1个A)    粘贴    粘贴    粘贴     粘贴    粘贴
        // A       A        A          2*A    3*A    4*A      5*A    6*A
        // 但是，注意看如下的行为，行为二：
        // 开始    全选    复制(1个A)    粘贴    全选  复制(2个A) 粘贴    粘贴
        // A       A        A          2*A    2*A    2*A      4*A    6*A
        // 行为一，最后是6*A
        // 行为二，最后是6*A
        // 但是行为二在粘贴板上有2个A，而行为一在粘贴板上有1个A
        // 所以行为一没有行为二更优
        // 以此说明：来到i的时候，包括i在内最多有连续4次粘贴行为
        // 那么就尝试：连续1次、连续2次、连续3次、连续4次粘贴行为即可
        for (int i = 6; i < n; i++) {
            int p1 = dp[i - 3] * 2;
            int p2 = dp[i - 4] * 3;
            int p3 = dp[i - 5] * 4;
            int p4 = dp[i - 6] * 5;
            dp[i] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
        return dp[n - 1];
    }
}
