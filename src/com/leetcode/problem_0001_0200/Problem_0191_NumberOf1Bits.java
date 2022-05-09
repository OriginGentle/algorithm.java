package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/3/18-8:45
 */
public class Problem_0191_NumberOf1Bits {

    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (n & 1) == 0 ? 0 : 1;
            n >>= 1;
        }
        return ans;
    }
}
