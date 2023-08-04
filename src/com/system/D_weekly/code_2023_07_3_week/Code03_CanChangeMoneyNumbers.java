package com.system.D_weekly.code_2023_07_3_week;

import java.io.*;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/7/25-22:45
 * @desc 每一种货币都给定面值val[i]，和拥有的数量cnt[i]
 * 想知道目前拥有的货币，在钱数为1、2、3...m时，能找零成功的钱数有多少
 * 也就是说当钱数的范围是1~m，返回这个范围上有多少可以找零成功的钱数
 * 比如只有3元的货币，数量是5张
 * m = 10
 * 那么在1~10范围上，只有钱数是3、6、9时，可以成功找零
 * 所以返回3，表示有3种钱数可以找零成功
 * 测试链接 : http://poj.org/problem?id=1742
 */
public class Code03_CanChangeMoneyNumbers {

    public static int MAXN = 101;

    public static int[] val = new int[MAXN];

    public static int[] cnt = new int[MAXN];

    public static int MAXM = 100001;

    public static boolean[] dp = new boolean[MAXM];

    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            if (n != 0 || m != 0) {
                for (int i = 1; i <= n; i++) {
                    in.nextToken();
                    val[i] = (int) in.nval;
                }

                for (int i = 0; i <= n; i++) {
                    in.nextToken();
                    cnt[i] = (int) in.nval;
                }

                out.println(compute());
            } else {
                out.flush();
            }
        }
    }

    private static int compute() {
        Arrays.fill(dp, 1, m + 1, false);
        dp[0] = true;
        // 遍历每一种货币
        for (int i = 1; i <= n; i++) {

            if (cnt[i] == 1) { // 当前货币就1张
                for (int j = m; j >= val[i]; j--) {
                    if (dp[j - val[i]]) {
                        dp[j] = true;
                    }
                }

            } else if (val[i] * cnt[i] > m) { // 当前货币无限张

                for (int j = val[i]; j <= m; j++) {
                    if (dp[j - val[i]]) {
                        dp[j] = true;
                    }
                }

            } else { // 既不是1张，也不是无限张

                for (int mod = 0; mod < val[i]; mod++) {
                    int trueCnt = 0;
                    for (int j = m - mod, size = 0; j >= 0 && size <= cnt[i]; j -= val[i], size++) {
                        trueCnt += dp[j] ? 1 : 0;
                    }

                    for (int j = m - mod, l = j - val[i] * (cnt[i] + 1);
                         j >= 1; j -= val[i], l -= val[i]) {
                        // dp[j] = 上一行的值
                        // dp[j] 更新成 本行的值
                        if (dp[j]) {
                            trueCnt--;
                        } else {
                            if (trueCnt != 0) {
                                dp[j] = true;
                            }
                        }
                        if (l >= 0) {
                            trueCnt += dp[l] ? 1 : 0;
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            if (dp[i]) {
                ans++;
            }
        }
        return ans;
    }

}
