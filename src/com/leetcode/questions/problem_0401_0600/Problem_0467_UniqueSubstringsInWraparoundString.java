package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/5/25-20:53
 */
public class Problem_0467_UniqueSubstringsInWraparoundString {

    public int findSubstringInWraproundString(String p) {
        char[] str = p.toCharArray();
        int n = str.length;
        int ans = 0;
        int[] max = new int[26];
        max[str[0] - 'a']++;
        for (int i = 1, j = 1; i < n; i++) {
            int cur = str[i] - 'a';
            int pre = str[i - 1] - 'a';
            if ((pre == 25 && cur == 0) || pre + 1 == cur) {
                j++;
            } else {
                j = 1;
            }
            max[cur] = Math.max(max[cur], j);
        }
        for (int i = 0; i < 26; i++) {
            ans += max[i];
        }
        return ans;
    }
}
