package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/12-10:09
 */
public class Problem_1781_SumOfBeautyOfAllSubstrings {

    public static int beautySum(String s) {
        int ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                cnt[c - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[c - 'a']);
                int minFreq = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0)
                        minFreq = Math.min(minFreq, cnt[k]);
                }
                ans += maxFreq - minFreq;
            }
        }
        return ans;
    }
}
