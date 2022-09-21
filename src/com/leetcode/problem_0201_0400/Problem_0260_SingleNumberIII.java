package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/21-16:27
 */
public class Problem_0260_SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }

        int k = -1;
        for (int i = 31; i >= 0 && k == -1; i++) {
            if (((xorSum >> i) & 1) == 1)
                k = i;
        }

        int[] ans = new int[2];
        for (int num : nums) {
            if (((num >> k) & 1) == 1)
                ans[1] ^= num;
            else
                ans[0] ^= num;
        }
        return ans;
    }
}
