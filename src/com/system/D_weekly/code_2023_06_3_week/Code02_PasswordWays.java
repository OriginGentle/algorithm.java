package com.system.D_weekly.code_2023_06_3_week;

/**
 * @author ycb
 * @date 2023/6/25-08:35
 * @desc 来自字节
 * 密码是一串长度为n的小写字母，一则关于密码的线索纸条
 * 首先将字母a到z编号为0到25编号
 * 纸条上共有n个整数ai，其中a1表示密码里第一个字母的编号
 * 若i>1的话就表示第i个字母和第i-1个字母编号的差值
 * 例如，a2就代表密码中第1个字母和第2个字母编号的差值
 * 若密码是acb，那么纸条上的数字就是[5, 2, 1]
 * a b c d e f
 * 0 1 2 3 4 5
 * 返回可能的密码的个数，由于结果可能很大，
 * 输出对 1000000007 取模的结果
 * 1 <= n <= 10^5
 * 0 <= ai <= 25
 */
public class Code02_PasswordWays {

    public static int ways(int[] arr) {

        return process1(arr, 1, arr[0]);
    }

    public static int process1(int[] arr, int i, int pre) {
        int ans = 0;
        if (pre < 0 || pre > 25) {
            return 0;
        }
        if (i == arr.length) {
            return 1;
        } else {
            ans += process1(arr, i + 1, pre - arr[i]);
            ans += process1(arr, i + 1, pre + arr[i]);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int ways2(int[] arr) {
        int mod = 1000000007;
        int n = arr.length;
        int[][] dp = new int[n + 1][26];
        for (int j = 0; j < 26; j++) {
            dp[n][j] = 1;
        }

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < 26; j++) {

                if (j - arr[i] >= 0) {
                    dp[i][j] = dp[i + 1][j - arr[i]];
                }

                if (j + arr[i] <= 25) {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j + arr[i]]) % mod;
                }
            }
        }

        return dp[1][arr[0]];
    }
}
