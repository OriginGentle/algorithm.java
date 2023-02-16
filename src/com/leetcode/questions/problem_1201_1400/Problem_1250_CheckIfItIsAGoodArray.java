package com.leetcode.questions.problem_1201_1400;

/**
 * @author ycb
 * @date 2023/2/16-10:59
 */
public class Problem_1250_CheckIfItIsAGoodArray {

    public static boolean isGoodArray(int[] nums) {
        long part = nums[0];
        for (int num : nums) {
            part = gcd(part, num);
            if (part == 1) {
                break;
            }
        }
        return part == 1;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
