package com.leetcode.problem_interview;

/**
 * @author ycb
 * @date 2022/5/13-08:24
 */
public class Problem_01_05_OneAwayLcci {

    public static boolean oneEditAway(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        int n = first.length();
        int m = second.length();
        if (Math.abs(n - m) > 1) {
            return false;
        }
        boolean used = false;
        for (int i = 0, j = 0; i < n && j < m; ) {
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else if (used) {
                return false;
            } else {
                if (m > n) {
                    j++;
                } else if (m < n) {
                    i++;
                } else {
                    i++;
                    j++;
                }
                used = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String f = "pale";
        String s = "ple";
        boolean ans = oneEditAway(f, s);
        System.out.println(ans);
    }
}
