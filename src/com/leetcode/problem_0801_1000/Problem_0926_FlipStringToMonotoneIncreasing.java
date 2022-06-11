package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/6/11-12:12
 */
public class Problem_0926_FlipStringToMonotoneIncreasing {

    public static int minFlipsMonoIncr(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (str[i - 1] - '0');
        }
        int ans = n;
        for (int i = 1; i <= n; i++) {
            // 左边有多少个1
            int left = sum[i - 1];
            // 右边有多少个0
            int right = (n - i) - (sum[n] - sum[i]);
            ans = Math.min(ans, left + right);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "00011000";
        int ans = minFlipsMonoIncr(s);
        System.out.println(ans);
    }
}
