package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/6/27-09:03
 */
public class Problem_0522_LongestUncommonSubsequenceII {

    public static int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check)
                ans = Math.max(ans, strs[i].length());
        }
        return ans;
    }

    public static boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ptS++;
            }
            ptT++;
        }
        return ptS == s.length();
    }
}
