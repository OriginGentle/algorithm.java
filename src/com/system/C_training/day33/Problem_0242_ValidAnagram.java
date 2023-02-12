package com.system.C_training.day33;

/**
 * @author ycb
 * @since 2021/10/21-8:33
 */
public class Problem_0242_ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] count = new int[256];
        for (char ch : str1) {
            count[ch]++;
        }
        for (char ch : str2) {
            if (--count[ch] < 0) {
                return false;
            }
        }
        return true;
    }
}
