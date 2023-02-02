package com.leetcode.problem_interview;

/**
 * @author ycb
 * @date 2022/9/27-08:23
 */
public class Problem_01_02_CheckPermutationLCCI {

    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        int[] map = new int[26];
        for (char c : s1.toCharArray()) {
            map[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0)
                return false;
        }
        return true;
    }
}
