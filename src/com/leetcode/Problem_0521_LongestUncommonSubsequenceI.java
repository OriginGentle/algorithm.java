package com.leetcode;

/**
 * @author ycb
 * @since 2022/3/5-19:23
 */
public class Problem_0521_LongestUncommonSubsequenceI {

    public static int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : a.length() > b.length() ? a.length() : b.length();
    }
}
