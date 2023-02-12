package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/8-11:20
 */
public class Problem_1684_CountTheNumberOfConsistentStrings {

    public int countConsistentStrings1(String allowed, String[] words) {
        boolean[] map = new boolean[26];
        for (char c : allowed.toCharArray()) {
            map[c - 'a'] = true;
        }

        int ans = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!map[word.charAt(i) - 'a'])
                    break;

                if (i == word.length() - 1)
                    ans++;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public int countConsistentStrings2(String allowed, String[] words) {
        int status = 0, ans = 0;
        for (char c : allowed.toCharArray()) {
            status |= 1 << (c - 'a');
        }

        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }

            if ((mask | status) == status)
                ans++;
        }
        return ans;
    }
}
