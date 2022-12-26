package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/26-09:05
 */
public class Problem_1759_CountNumberOfHomogenousSubStrings {

    public static final int MOD = (int) 1e9 + 7;

    public static int countHomogenous(String s) {
        long ans = 0;
        char pre = s.charAt(0);
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == pre) {
                cnt++;
            } else {
                ans += (long) (cnt + 1) * cnt / 2;
                cnt = 1;
                pre = c;
            }
        }

        ans += (long) (cnt + 1) * cnt / 2;
        return (int) (ans % MOD);
    }
}
