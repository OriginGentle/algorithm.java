package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/17-12:12
 */
public class Problem_1220_CountVowelsPermutation {

    private static final int MOD = (int) 1e9 + 7;

    // dp[i][j] 表示考虑了前 ii 个字符，并且第 ii 个字符为 jj 的方案数。
    public static int countVowelPermutation1(int n) {
        long[][] dp = new long[2][5];
        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }
        int flag = 0;
        for (int i = 2; i <= n; i++) {
            flag ^= 1;
            // a 前面可以为 e u i
            dp[flag ^ 1][0] = (dp[flag][1] + dp[flag][2] + dp[flag][4]) % MOD;
            // e 前面可以为 a i
            dp[flag ^ 1][1] = (dp[flag][0] + dp[flag][2]) % MOD;
            // i 前面可以为 e o
            dp[flag ^ 1][2] = (dp[flag][1] + dp[flag][3]) % MOD;
            // o 前面可以为 i
            dp[flag ^ 1][3] = dp[flag][2];
            // u 前面可以为 i,o
            dp[flag ^ 1][4] = (dp[flag][2] + dp[flag][3]) % MOD;
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[flag ^ 1][i]) % MOD;
        }
        return (int) ans;
    }

    /*
    ====================================================================================================================
     */

    public int countVowelPermutation2(int n) {
        long mod = 1_000_000_007;
        long[][] factor =
                {
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 0, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0}
                };

        long[][] res = fastPow(factor, n - 1, mod);
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int) ans;
    }

    public long[][] fastPow(long[][] matrix, int n, long mod) {
        int m = matrix.length;
        long[][] res = new long[m][m];
        long[][] curr = matrix;

        for (int i = 0; i < m; ++i) {
            res[i][i] = 1;
        }
        for (int i = n; i != 0; i >>= 1) {
            if ((i % 2) == 1) {
                res = multiply(curr, res, mod);
            }
            curr = multiply(curr, curr, mod);
        }
        return res;
    }

    public long[][] multiply(long[][] matrixA, long[][] matrixB, long mod) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        long[][] res = new long[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < matrixA[i].length; ++k) {
                    res[i][j] = (res[i][j] + matrixA[i][k] * matrixB[k][j]) % mod;
                }
            }
        }
        return res;
    }
}
