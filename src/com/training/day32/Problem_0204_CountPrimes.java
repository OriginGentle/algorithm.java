package com.training.day32;

/**
 * @author ycb
 * @since 2021/10/19-8:20
 */
public class Problem_0204_CountPrimes {

    // 插空法
    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        // j已经不是素数了，f[j] = true;
        boolean[] f = new boolean[n];
        // 所有偶数都不要，还剩几个数
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}
