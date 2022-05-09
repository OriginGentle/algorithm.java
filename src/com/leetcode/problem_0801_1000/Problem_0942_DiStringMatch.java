package com.leetcode.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/5/9-08:07
 */
public class Problem_0942_DiStringMatch {

    public static int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int l = 0;
        int r = n;
        for (int i = 0; i < n; i++) {
            ans[i] = s.charAt(i) == 'I' ? l++ : r--;
        }
        ans[n] = l;
        return ans;
    }

    public static void main(String[] args) {
        String s = "IDID";
        int[] ans = diStringMatch(s);
        for (int n : ans) {
            System.out.print(n + " ");
        }
    }
}
