package com.system.III_training.day32;

/**
 * @author ycb
 * @since 2021/10/19-8:19
 */
public class Problem_0172_FactorialTrailingZeroes {

    public static int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
