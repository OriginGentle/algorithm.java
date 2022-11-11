package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/11-08:26
 */
public class Problem_1704_DetermineIfStringHalvesAreAlike {

    public boolean halvesAreAlike(String s) {
        if (s == null || s.length() == 0)
            return true;

        int L = 0, R = s.length() >> 1, lCount = 0, rCount = 0;
        for (; R < s.length(); R++, L++) {
            if (isVowel(s.charAt(L)))
                lCount++;

            if (isVowel(s.charAt(R)))
                rCount++;
        }
        return lCount == rCount;
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
