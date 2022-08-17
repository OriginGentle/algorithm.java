package com.leetcode.problem_0001_0200;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/8/17-08:56
 */
public class Problem_0179_LargestNumber {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(str[i]);
        }
        //  处理前导为0的情况
        int k = 0, len = sb.length();
        while (k < len - 1 && sb.charAt(k) == '0') {
            k++;
        }
        return sb.substring(k);
    }
}
