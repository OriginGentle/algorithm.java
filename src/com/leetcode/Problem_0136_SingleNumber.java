package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/9
 */
public class Problem_0136_SingleNumber {

    public int singleNumber(int[] nums) {
        int num = 0;
        for (int n : nums) {
            num ^= n;
        }
        return num;
    }
}
