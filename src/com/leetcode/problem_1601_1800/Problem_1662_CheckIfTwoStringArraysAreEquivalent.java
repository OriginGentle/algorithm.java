package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/1-08:25
 */
public class Problem_1662_CheckIfTwoStringArraysAreEquivalent {

    public boolean arrayStringsAreEqual(String[] s1, String[] s2) {
        int p1 = 0, p2 = 0, i1 = 0, i2 = 0;
        while (p1 < s1.length && p2 < s2.length) {
            if (s1[p1].charAt(i1) != s2[p2].charAt(i2))
                return false;
            i1++;
            if (i1 == s1[p1].length()) {
                p1++;
                i1 = 0;
            }
            i2++;
            if (i2 == s2[p2].length()) {
                p2++;
                i2 = 0;
            }
        }
        return p1 == s1.length && p2 == s2.length;
    }
}
