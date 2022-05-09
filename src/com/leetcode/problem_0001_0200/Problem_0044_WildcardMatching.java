package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/6-10:41
 */
public class Problem_0044_WildcardMatching {

    // dp[i][j] : s的前i个字符和p的前j个字符是否匹配
    public static boolean isMatch1(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // dp[i][0] == false
        // dp[0][j] : '*'可以匹配空
        // s == " "时，只有 '*'可以与之匹配
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') { // 当前位置是'*'
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) { // 当前位置不是'*'
                    // 第一种情况：p当前位置是'?',对s是没有要求的
                    // 第二种情况：当前位置不是'?',则s对应位置必须匹配
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    /*
    ====================================================================================================================
     */

    public static boolean isMatch2(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int N = str.length;
        int M = pattern.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int pi = M - 1; pi >= 0; pi--) {
            if (pattern[pi] != '*') {
                break;
            }
            dp[N][pi] = true;
        }
        for (int pi = M - 1; pi >= 0; pi--) {
            for (int si = N - 1; si >= 0; si--) {
                if (pattern[pi] != '*') {
                    dp[si][pi] = (str[si] == pattern[pi] || pattern[pi] == '?') && dp[si + 1][pi + 1];
                } else {
                    dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
                }
            }
        }
        return dp[0][0];
    }

    /*
    ====================================================================================================================
     */

    // p的表现形式：
    // 1) *kl*pi*ju*
    // 2) *klllp 或者 kpaoak*
    public static boolean isMatch3(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int se = str.length, pe = pattern.length;
        while (se > 0 && pe > 0 && pattern[pe - 1] != '*') {
            if (isValid(str[se - 1], pattern[pe - 1])) {
                --se;
                --pe;
            } else {
                return false;
            }
        }
        if (pe == 0) {
            return se == 0;
        }
        int si = 0, pi = 0, sr = -1, pr = -1;
        while (si < se && pi < pe) {
            if (pattern[pi] == '*') { // 当前位置是'*'
                ++pi;
                sr = si;
                pr = pi;
            } else if (isValid(str[si], pattern[pi])) { // 不是'*',并且能匹配上
                ++si;
                ++pi;
            } else if (sr != -1 && sr + 1 < se) { // 匹配不上
                ++sr;
                si = sr;
                pi = pr;
            } else {
                return false;
            }
        }
        return restIsValid(pattern, pi, pe);
    }

    public static boolean restIsValid(char[] p, int l, int r) {
        for (int i = l; i < r; i++) {
            if (p[i] != '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(char s, char p) {
        return s == p || p == '?';
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a";
        boolean ans1 = isMatch3(s, p);
        System.out.println(ans1);
    }
}
