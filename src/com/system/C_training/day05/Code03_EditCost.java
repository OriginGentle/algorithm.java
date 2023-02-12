package com.system.C_training.day05;

/**
 * @Author ycb
 * @Date 2021/8/4-8:04
 * @Description 经典中的经典:编辑距离问题
 */
public class Code03_EditCost {

    // 动态规划:样本对应模型
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        int[][] dp = new int[N][M];
        // dp[i][j]: str1取前i个字符,str2取前j个字符，花费的最小代价是多少
        for (int i = 0; i < N; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 0; i < M; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                /**
                 * dp[i][j] : str[0...i-1] str2[0...j-1]
                 * 情况1：str1完全不考虑i-1位置字符，让其前缀串编辑成str2 --> dp[i-1][j] + dc
                 * 情况2：str1的整体变成str2的前j-1串，最后在str1后面补上一个字符 -- dp[i][j-1] + ic
                 * 情况3：str1[i - 1] == str2[j - 1] --> dp[i-1][j-1]
                 * 情况4：str1[i - 1] != str2[j - 1] --> dp[i-1][j-1] + rc
                 */
                dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[N - 1][M - 1];
    }

    /*
    ====================================================================================================================
     */

    // 空间压缩
    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));
    }

}
