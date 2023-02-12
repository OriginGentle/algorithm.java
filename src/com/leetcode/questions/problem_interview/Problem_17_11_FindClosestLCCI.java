package com.leetcode.questions.problem_interview;

/**
 * @author ycb
 * @date 2022/5/27-09:01
 */
public class Problem_17_11_FindClosestLCCI {

    public int findClosest(String[] words, String s1, String s2) {
        int ans = Integer.MAX_VALUE;
        int n = words.length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < n; i++) {
            String cur = words[i];
            if (s1.equals(cur)) {
                index1 = i;
            } else if (s2.equals(cur)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                ans = Math.min(ans, Math.abs(index1 - index2));
            }
        }
        return ans;
    }
}
