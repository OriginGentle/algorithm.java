package com.system.D_weekly.code_2022_06_1_week;

/**
 * @author ycb
 * @date 2022/6/11-16:16
 * @desc 把字符串 s 看作 "abcdefghijklmnopqrstuvwxyz"的无限环绕字符串，
 * 所以 s 看起来是这样的：
 * ...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....
 * 现在给定另一个字符串 p 。返回 s 中 不同 的 p 的 非空子串的数量
 * <p>
 * https://leetcode.com/problems/unique-substrings-in-wraparound-string/
 */
public class Code02_UniqueSubstringsInWraparoundString {

    public static int findSubstringInWraproundString(String p) {
        char[] str = p.toCharArray();
        int ans = 0;
        int len = 1;
        int n = str.length;
        int[] max = new int[256];
        max[str[0]]++;
        for (int i = 1; i < n; i++) {
            char cur = str[i];
            char pre = str[i - 1];
            if ((cur == 'a' && pre == 'z') || pre + 1 == cur) {
                len++;
            } else {
                len = 1;
            }
            max[cur] = Math.max(max[cur], len);
        }
        for (int i = 0; i < max.length; i++) {
            ans += max[i];
        }
        return ans;
    }
}
