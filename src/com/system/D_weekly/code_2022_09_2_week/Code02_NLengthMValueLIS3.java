package com.system.D_weekly.code_2022_09_2_week;

/**
 * @author ycb
 * @date 2022/9/15-10:24
 * @desc 来自微众银行
 * 给定一个数字n，代表数组的长度
 * 给定一个数字m，代表数组每个位置都可以在1~m之间选择数字
 * 所有长度为n的数组中，最长递增子序列长度为3的数组，叫做达标数组
 * 返回达标数组的数量
 * 1 <= n <= 500
 * 1 <= m <= 10
 * 500 * 10 * 10 * 10
 */
public class Code02_NLengthMValueLIS3 {

    public static int number1(int n, int m) {
        return process1(0, n, m, new int[n]);
    }

    private static int process1(int i, int n, int m, int[] path) {
        if (i == n)
            return lenOfLIS(path) == 3 ? 1 : 0;

        else {
            int ans = 0;
            for (int cur = 1; cur <= m; cur++) {
                path[i] = cur;
                ans += process1(i + 1, n, m, path);
            }
            return ans;
        }
    }


    public static int lenOfLIS(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int[] ends = new int[arr.length];
        ends[0] = arr[0];

        int right = 0, max = 0;

        for (int num : arr) {
            int l = 0, r = right;
            while (l <= r) {
                int m = (l + r) >> 1;

                if (num > ends[m])
                    l = m + 1;
                else
                    r = m - 1;
            }

            right = Math.max(right, l);
            ends[l] = num;
            max = Math.max(max, l + 1);
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    public static int number2(int n, int m) {
        int[][][][] dp = new int[n][m + 1][m + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= m; k++) {
                    for (int l = 0; l <= m; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }

        return process2(0, 0, 0, 0, n, m, dp);
    }

    public static int process2(int i, int f, int s, int t, int n, int m, int[][][][] dp) {
        if (i == n)
            return f != 0 && s != 0 && t != 0 ? 1 : 0;

        if (dp[i][f][s][t] != -1)
            return dp[i][f][s][t];

        int ans = 0;
        for (int cur = 1; cur <= m; cur++) {
            if (f == 0 || cur <= f)
                ans += process2(i + 1, cur, s, t, n, m, dp);

            else if (s == 0 || cur <= s)
                ans += process2(i + 1, f, cur, t, n, m, dp);

            else if (t == 0 || cur <= t)
                ans += process2(i + 1, f, s, cur, n, m, dp);
        }

        dp[i][f][s][t] = ans;
        return ans;
    }

    // for test
    public static void main(String[] args) {
        System.out.println("功能测试开始");
        for (int n = 4; n <= 8; n++) {
            for (int m = 1; m <= 5; m++) {
                int ans1 = number1(n, m);
                int ans2 = number2(n, m);
                if (ans1 != ans2) {
                    System.out.println(ans1);
                    System.out.println(ans2);
                    System.out.println("出错了!");
                }
            }
        }
        System.out.println("功能测试结束");
        System.out.println("性能测试开始");
        int n = 2000;
        int m = 20;
        System.out.println("序列长度 : " + n);
        System.out.println("数字范围 : " + m);
        long start = System.currentTimeMillis();
        number2(n, m);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }
}
