package com.leetcode.problem_0801_1000;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/6-09:18
 */
public class Problem_0828_CountUniqueCharactersOfAllSubstringsOfAGivenString {

    public int uniqueLetterString(String s) {
        int n = s.length();

        int[][] cnt = new int[26][2];
        for (int[] c : cnt)
            Arrays.fill(c, -1);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'A';
            if (cnt[idx][1] != -1) {
                ans += (cnt[idx][1] - cnt[idx][0]) * (i - cnt[idx][1]);
                cnt[idx][0] = cnt[idx][1];
            }
            cnt[idx][1] = i;
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i][1] != -1)
                ans += (cnt[i][1] - cnt[i][0]) * (n - cnt[i][1]);
        }
        return ans;
    }
}
