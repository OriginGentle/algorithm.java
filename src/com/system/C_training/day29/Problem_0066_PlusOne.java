package com.system.C_training.day29;

/**
 * @author ycb
 * @since 2021/10/16-12:05
 */
public class Problem_0066_PlusOne {

    public static int[] plusOne(int[] digits) {
        int N = digits.length;
        for (int i = N - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }
}
