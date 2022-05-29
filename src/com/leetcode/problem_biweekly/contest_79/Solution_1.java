package com.leetcode.problem_biweekly.contest_79;

public class Solution_1 {

    public static boolean digitCount(String num) {
        int[] count = new int[10];
        char[] str = num.toCharArray();
        for (char ch : str) {
            count[ch - '0']++;
        }
        boolean ans = true;
        for (int i = 0; i < str.length; i++) {
            int cur = str[i] - '0';
            if (count[i] != cur) {
                ans = false;
                break;
            }
        }
        return ans;
    }
}
