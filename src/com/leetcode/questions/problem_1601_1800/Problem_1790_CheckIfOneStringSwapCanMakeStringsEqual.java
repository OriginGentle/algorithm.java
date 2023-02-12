package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/3/20-20:47
 */
public class Problem_1790_CheckIfOneStringSwapCanMakeStringsEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        char c1 = '#', c2 = '#';
        boolean hasChange = false;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (hasChange) {
                    return false;
                } else if (c1 == '#') {
                    c1 = s1.charAt(i);
                    c2 = s2.charAt(i);
                } else {
                    if (c1 == s2.charAt(i) && c2 == s1.charAt(i)) {
                        hasChange = true;
                        c2 = '#';
                    } else {
                        return false;
                    }
                }
            }
        }
        return c2 == '#';
    }
}
