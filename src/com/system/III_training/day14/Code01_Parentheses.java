package com.system.III_training.day14;

/**
 * @author ycb
 * @date 2021/9/8-9:10
 * @description https://leetcode.com/problems/valid-parentheses/
 */
public class Code01_Parentheses {

    // s只有 '(' 和 ')' 组成
    // 求最长有效括号子串长度
    // dp ：求每个位置结尾的有效括号子串长度
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i] : 子串必须以i位置结尾的情况下，往左最远能扩出多长的有效区域
        int[] dp = new int[str.length];
        // dp[0] = 0;
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                // 当前谁和i位置')'去匹配
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
