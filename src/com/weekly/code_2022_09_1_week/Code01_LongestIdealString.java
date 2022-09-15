package com.weekly.code_2022_09_1_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/14-13:51
 * @desc 给你一个由小写字母组成的字符串 s ，和一个整数 k
 * 如果满足下述条件，则可以将字符串 t 视作是 理想字符串 ：
 * t 是字符串 s 的一个子序列。
 * t 中每两个相邻字母在字母表中位次的绝对差值小于或等于 k 。
 * 返回最长理想字符串的长度。
 * 字符串的子序列同样是一个字符串，并且子序列还满足：
 * 可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。
 * 注意：字母表顺序不会循环
 * 例如，'a' 和 'z' 在字母表中位次的绝对差值是 25，而不是 1 。
 * <p>
 * https://leetcode.cn/problems/longest-ideal-subsequence/
 */
public class Code01_LongestIdealString {

    public static int longestIdealString1(String s, int k) {
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int[][] dp = new int[n][27];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return func(arr, 0, 26, k, dp);
    }

    // 数组s中所有的值都在0~25对应a~z
    // 当前在s[i...]选择数字, 并且前一个数字是p
    // 如果p<26，说明选择的前一个数字是p
    // 如果p==26，说明之前没有选过任何数字
    // 返回在前一个数字是p的情况下，在s[i...]上选择数字，最长理想子序列能是多长
    private static int func(int[] s, int i, int p, int k, int[][] dp) {
        if (i == s.length)
            return 0;

        if (dp[i][p] != -1)
            return dp[i][p];

        int p1 = func(s, i + 1, p, k, dp);
        int p2 = 0;
        if (p == 26 || Math.abs(s[i] - p) <= k)
            p2 = 1 + func(s, i + 1, s[i], k, dp);

        int ans = Math.max(p1, p2);
        dp[i][p] = ans;

        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int longestIdealString2(String s, int k) {
        int[] dp = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            int l = Math.max(c - k, 0), r = Math.min(c + k, 25);

            int pre = 0;
            for (int j = l; j <= r; j++) {
                pre = Math.max(pre, dp[j]);
            }

            dp[c] = ++pre;
            ans = Math.max(dp[c], ans);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int longestIdealString3(String s, int k) {
        SegmentTree seg = new SegmentTree(26);

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a' + 1;
            int pre = seg.max(
                    Math.max(c - k, 1), Math.min(c + k, 26));

            ans = Math.max(ans, pre + 1);
            seg.update(c, ++pre);
        }
        return ans;
    }

    public static class SegmentTree {
        private final int n;
        private final int[] max;

        public SegmentTree(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
        }

        public void update(int index, int val) {
            update(index, index, val, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                return;
            }

            int m = (l + r) >> 1;

            if (L <= m)
                update(L, R, C, l, m, rt << 1);

            if (R > m)
                update(L, R, C, m + 1, r, rt << 1 | 1);

            pushUp(rt);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }


        public int max(int left, int right) {
            return max(left, right, 1, n, 1);
        }

        private int max(int s, int e, int l, int r, int rt) {
            if (s <= l && r <= e) {
                return max[rt];
            }

            int m = (l + r) >> 1;
            int ans = 0;

            if (s <= m)
                ans = Math.max(ans, max(s, e, l, m, rt << 1));

            if (e > m)
                ans = Math.max(ans, max(s, e, m + 1, r, rt << 1 | 1));
            return ans;
        }
    }
}
