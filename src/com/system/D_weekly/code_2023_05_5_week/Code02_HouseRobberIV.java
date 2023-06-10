package com.system.D_weekly.code_2023_05_5_week;

/**
 * @author ycb
 * @date 2023/6/1-23:09
 * @desc 沿街有一排连续的房屋。每间房屋内都藏有一定的现金
 * 现在有一位小偷计划从这些房屋中窃取现金
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额
 * 形式上，从左起第 i 间房屋中放有 nums[i] 美元
 * 另给你一个整数 k ，表示窃贼将会窃取的最少房屋数
 * 小偷一定要要窃取至少 k 间房屋，返回小偷的 最小 窃取能力
 * 测试链接 : https://leetcode.cn/problems/house-robber-iv/
 */
public class Code02_HouseRobberIV {

    public static int minCapability(int[] nums, int k) {
        int l = 1, r = 0;
        for (int num : nums) {
            r = Math.max(num, r);
        }

        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (robber(nums, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 当盗贼的能力为 ability 时，返回盗贼最多能窃取多少间房屋
    // 注意不能窃取相邻房屋
    private static int robber(int[] nums, int ability) {
        int lastLast = nums[0] <= ability ? 1 : 0;
        int n = nums.length;
        if (n == 1) {
            return lastLast;
        }

        int last = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
        int ans = last;
        for (int i = 2; i < n; i++) {

            int p1 = last;
            int p2 = 0;
            if (nums[i] <= ability) {
                p2 = lastLast + 1;
            }

            int cur = Math.max(p1, p2);
            ans = Math.max(cur, ans);

            lastLast = last;
            last = cur;
        }
        return ans;
    }
}
