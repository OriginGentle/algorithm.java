package com.system.IV_weekly.code_2022_07_1_week;

/**
 * @author ycb
 * @date 2022/7/10-16:08
 * @desc https://leetcode.cn/problems/number-of-people-aware-of-a-secret/
 */
public class Code03_NumberOfPeopleAwareOfASecret {

    private static final long MOD = 1000000007;

    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        // dpKnow[i], 第i天知道秘密的人
        long[] dpKnow = new long[n + 1];
        // dpForget[i], 第i天将要忘记秘密的人
        long[] dpForget = new long[n + 1];
        // dpShare[i], 第i天可以分享秘密的人
        long[] dpShare = new long[n + 1];
        dpKnow[1] = 1;
        if (1 + delay <= n) {
            dpShare[1 + delay] = 1;
        }
        if (1 + forget <= n) {
            dpForget[1 + forget] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dpKnow[i] = (dpKnow[i - 1] + dpShare[i] - dpForget[i] + MOD) % MOD;
            if (i + forget <= n) {
                dpForget[i + forget] = dpShare[i];
            }
            if (i + delay <= n) {
                dpShare[i + delay] = (MOD + dpShare[i + delay - 1] + dpShare[i] - dpForget[i + delay]) % MOD;
            }
        }
        return (int) dpKnow[n];
    }
}
