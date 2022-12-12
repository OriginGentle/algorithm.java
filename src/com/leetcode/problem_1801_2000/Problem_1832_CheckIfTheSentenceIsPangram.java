package com.leetcode.problem_1801_2000;

/**
 * @author ycb
 * @date 2022/12/13-00:16
 */
public class Problem_1832_CheckIfTheSentenceIsPangram {

    public boolean checkIfPangram(String sentence) {
        boolean[] cnt = new boolean[26];
        for (int i = 0; i < sentence.length(); i++) {
            cnt[sentence.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if (!cnt[i])
                return false;
        }
        return true;
    }
}
