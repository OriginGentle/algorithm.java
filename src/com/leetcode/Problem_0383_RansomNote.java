package com.leetcode;

/**
 * @author ycb
 * @since 2021/12/6-8:56
 */
public class Problem_0383_RansomNote {

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char ch : ransomNote.toCharArray()) {
            count[ch - 'a']--;
            if (count[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
