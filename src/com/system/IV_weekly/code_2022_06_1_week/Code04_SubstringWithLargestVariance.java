package com.system.IV_weekly.code_2022_06_1_week;

/**
 * @author ycb
 * @date 2022/6/11-16:16
 * @desc 字符串的 波动定义为子字符串中出现次数 最多的字符次数与出现次数 最少的字符次数之差。
 * 给你一个字符串 s ，它只包含小写英文字母。请你返回 s 里所有 子字符串的最大波动值。
 * 子字符串 是一个字符串的一段连续字符序列。
 * 注意：必须同时有，最多字符和最少字符的字符串才是有效的
 * <p>
 * https://leetcode.cn/problems/substring-with-largest-variance/
 */
public class Code04_SubstringWithLargestVariance {

    public static int largestVariance1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // a b b c a
        // 0 1 1 2 0
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int ans = 0;
        for (int more = 0; more < 26; more++) {
            for (int less = 0; less < 26; less++) {

                if (more != less) {
                    int continuousA = 0;
                    boolean appearB = false;
                    int max = 0;
                    for (int i = 0; i < n; i++) {
                        if (arr[i] != more && arr[i] != less) {
                            continue;
                        }

                        if (arr[i] == more) { // 当前字符是more
                            continuousA++;
                            if (appearB) {
                                max++;
                            }
                        } else { // 当前字符是less
                            max = Math.max(max, continuousA) - 1;
                            continuousA = 0;
                            appearB = true;
                        }

                        ans = Math.max(ans, max);
                    }
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int largestVariance2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        // dp[a][b] = more a less b max
        // dp[b][a] = more b less a max
        int[][] dp = new int[26][26];

        // continuous[a][b] more a less b 连续出现a的次数
        // continuous[b][a] more b less a 连续出现b的次数
        int[][] continuous = new int[26][26];

        // appear[a][b] more a less b b有没有出现过
        // appear[b][a] more b less a a有没有出现过
        boolean[][] appear = new boolean[26][26];
        int ans = 0;

        for (int i : arr) {
            for (int j = 0; j < 26; j++) {
                if (j != i) {

                    ++continuous[i][j];
                    if (appear[i][j]) {
                        ++dp[i][j];
                    }
                    // i , j
                    // more i less j 三个变量 连续出现i，j有没有出现过，i-j max
                    // more j less i 三个变量 连续出现j，i有没有出现过，j-i max
                    if (!appear[j][i]) {
                        appear[j][i] = true;
                        dp[j][i] = continuous[j][i] - 1;
                    } else {
                        dp[j][i] = Math.max(continuous[j][i], dp[j][i]) - 1;
                    }
                    continuous[j][i] = 0;
                    ans = Math.max(ans, Math.max(dp[j][i], dp[i][j]));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aababbb";
        int ans = largestVariance1(s);
        System.out.println(ans);
    }

}
