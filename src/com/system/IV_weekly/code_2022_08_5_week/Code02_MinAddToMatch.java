package com.system.IV_weekly.code_2022_08_5_week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ycb
 * @date 2022/9/2-16:49
 * @desc 给定一个由 '[' ，']'，'('，‘)’ 组成的字符串
 * 请问最少插入多少个括号就能使这个字符串的所有括号左右配对
 * 例如当前串是 "([[])"，那么插入一个']'即可满足
 * 输出最少插入多少个括号
 * <p>
 * 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
 */
public class Code02_MinAddToMatch {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(
                    minAdd(line.toCharArray())
            );
        }
    }

    public static int minAdd(char[] str) {
        int n = str.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return process(str, 0, n - 1, dp);
    }

    // 让 s[L...R]完美匹配
    // 至少增加几个字符
    private static int process(char[] s, int L, int R, int[][] dp) {
        // 只有一个字符了，要想配对，必须增加一个字符
        if (L == R)
            return 1;

        // 剩下两个字符，如果刚好匹配，则不用增加字符
        // 否则，必须增加两个字符与之匹配
        if (L == R - 1) {
            if ((s[L] == '(' && s[R] == ')') ||
                    (s[L] == '[' && s[R] == ']'))
                return 0;

            return 2;
        }

        if (dp[L][R] != -1)
            return dp[L][R];

        // 先搞定 L + 1 ... R
        // 比如 [()()，先搞定()()
        // 最后增加一个字符搞定 [
        int p1 = 1 + process(s, L + 1, R, dp);

        // 和 p1 同理
        int p2 = 1 + process(s, L, R - 1, dp);

        int p3 = Integer.MAX_VALUE;
        if ((s[L] == '(' && s[R] == ')') ||
                (s[L] == '[' && s[R] == ']'))
            p3 = process(s, L + 1, R - 1, dp);

        int ans = Math.min(p1, Math.min(p2, p3));

        // 后续可能性：L ... R之间并不是嵌套关系
        // 而是并列关系
        for (int split = L; split < R; split++) {
            ans = Math.min(ans,
                    process(s, L, split, dp) + process(s, split + 1, R, dp));
        }

        dp[L][R] = ans;
        return ans;
    }
}
