package com.system.D_weekly.code_2023_03_3_week;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2023/3/16-10:53
 * @desc 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空）
 * 使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * 子数组 定义为原数组中连续的一组元素。
 * 测试链接 : https://leetcode.cn/problems/make-sum-divisible-by-p/
 */
public class Code03_MakeSumDivisibleByP {

    public int minSubarray(int[] nums, int p) {
        int n = nums.length, allMod = 0;
        for (int num : nums) {
            allMod = (allMod + num) % p;
        }

        if (allMod == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = Integer.MAX_VALUE, curMod = 0, find;
        for (int i = 0; i < n; i++) {
            curMod = (curMod + nums[i]) % p;
            find = (curMod - allMod + p) % p;
            if (map.containsKey(find)) {
                if (i != n - 1 || map.get(find) != -1) {
                    ans = Math.min(ans, i - map.get(find));
                }
            }
            map.put(curMod, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
