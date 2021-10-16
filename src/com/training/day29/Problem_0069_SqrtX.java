package com.training.day29;

/**
 * @author ycb
 * @since 2021/10/16-12:06
 */
public class Problem_0069_SqrtX {

    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x <= 3) {
            return 1;
        }
        long L = 1;
        long R = x >> 1;
        long M = 0;
        long ans = 1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (M * M <= x) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return (int) ans;
    }
}
