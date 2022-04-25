package com.leetcode;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/4/13
 */
public class Problem_0115_DistinctSubsequences {

    public static int numDistinct1(String s, String t) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        ArrayList<String> path = new ArrayList<>();
        process(str, 0, "", path);
        int ans = 0;
        for (String cur : path) {
            if (cur.equals(t)) {
                ans++;
            }
        }
        return ans;
    }

    public static void process(char[] str, int index, String preS, ArrayList<String> path) {
        if (index == str.length) {
            return;
        }
        process(str, index + 1, preS, path);
        String cur = preS;
        preS += str[index];
        path.add(preS);
        process(str, index + 1, preS, path);
        preS = cur;
    }

    /*
    ====================================================================================================================
     */

    // 样本对应模型
    public static int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (n > m) {
            return 0;
        }
        // dp[i][j] : t的前i个字符串，可以被s的前j个字符串组成的最多个数
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++) {
            // t为空时，s不管选多少个，都有1个答案，那就是什么都不选
            dp[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            // s什么都不选时，t只要有字符串，那就没有答案
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    /*
    ====================================================================================================================
     */

    public static int numDistinct3(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return 0;
        }
        // dp[i][j] = s[i:] 的子序列中 t[j:]出现的个数
        // s[i:] 表示 s 从下标 i 到末尾的子字符串，t[j:] 表示 t 从下标 j 到末尾的子字符串
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sc = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tc = t.charAt(j);
                if (sc == tc) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        int ans = numDistinct1(s, t);
        System.out.println(ans);
    }
}
