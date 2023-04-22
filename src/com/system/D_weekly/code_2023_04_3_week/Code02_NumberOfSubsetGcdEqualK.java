package com.system.D_weekly.code_2023_04_3_week;

import java.io.*;

/**
 * @author ycb
 * @date 2023/4/22-11:59
 * @desc 来自腾讯笔试
 * 给定一个长度为N的正数数组，还有一个正数K
 * 返回有多少子序列的最大公约数为K
 * 结果可能很大，对1000000007取模
 * 原题目简单转化就是如下的题目
 * 测试链接 : https://www.luogu.com.cn/problem/CF803F
 */
public class Code02_NumberOfSubsetGcdEqualK {

    public static int MAXN = 100001;

    public static int MOD = 1000000007;

    public static long[] dp = new long[MAXN];

    public static long[] cnt = new long[MAXN];

    public static long[] pow2 = new long[MAXN];

    public static int n, v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            pow2[0] = 1;
            for (int i = 1; i <= n; i++) {
                in.nextToken();
                v = (int) in.nval;
                cnt[v]++;
                pow2[i] = pow2[i - 1] * 2 % MOD;
            }

            for (int i = MAXN - 1; i >= 1; i--) {
                long counts = 0;
                for (int j = i; j < MAXN; j += i) {
                    counts = (counts + cnt[j]) % MOD;
                }

                dp[i] = (pow2[(int) counts] - 1 + MOD) % MOD;
                for (int j = 2 * i; j < MAXN; j += i) {
                    dp[i] = (dp[i] - dp[j] + MOD) % MOD;
                }
            }
            out.println(dp[1]);
            out.flush();
        }
    }
}
