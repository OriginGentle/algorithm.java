package com.system.IV_weekly.code_2022_07_2_week;

/**
 * @author ycb
 * @date 2022/7/16-23:29
 * @desc https://leetcode.com/problems/distinct-subsequences-ii/
 */
public class Code01_DistinctSubseqValue {

    private static final int MOD = 1000000007;

    public static int distinctSubseqII(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int all = 1;
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            int add = (all - count[c - 'a'] + MOD) % MOD;
            all = (all + add) % MOD;
            count[c - 'a'] = (count[c - 'a'] + add) % MOD;
        }
        return all - 1;
    }
}
