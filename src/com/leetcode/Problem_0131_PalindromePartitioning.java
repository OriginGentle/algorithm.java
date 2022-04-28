package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/4/27-11:45
 */
public class Problem_0131_PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            List<String> cnt = new ArrayList<>();
            for (int j = i; j < s.length(); j++) {
                String cur = s.substring(i, j + 1);
                if (isPalindrome(cur)) {
                    cnt.add(cur);
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    public static boolean isPalindrome(String str) {
        int l = 0;
        int r = str.length() - 1;
        while (l <= r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "ada";
        boolean ans = isPalindrome(str);
        System.out.println(ans);
    }
}
