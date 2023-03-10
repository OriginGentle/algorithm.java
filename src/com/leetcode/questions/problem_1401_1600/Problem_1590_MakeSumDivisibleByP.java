package com.leetcode.questions.problem_1401_1600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2023/3/10-11:10
 */
public class Problem_1590_MakeSumDivisibleByP {

    // 整除:等价于 % p = 0
    public int minSubarray(int[] nums, int p) {
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num) % p;
        }

        if (sum % p == 0) {
            return 0;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        int y = 0, ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            idxMap.put(y, i);
            y = (y + nums[i]) % p;
            if (idxMap.containsKey((y - sum + p) % p)) {
                ans = Math.min(ans, i - idxMap.get((y - sum + p) % p) + 1);
            }
        }
        return ans == nums.length ? -1 : ans;
    }
}
