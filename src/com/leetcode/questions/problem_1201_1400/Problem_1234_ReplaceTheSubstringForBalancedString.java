package com.leetcode.questions.problem_1201_1400;

/**
 * @author ycb
 * @date 2023/2/13-08:27
 */
public class Problem_1234_ReplaceTheSubstringForBalancedString {

    public static int balancedString(String s) {
        if (s == null || s.length() % 4 != 0) {
            return -1;
        }
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'A']++;
        }

        int target = s.length() / 4, ans = s.length();
        for (int l = 0, r = 0; l < s.length(); l++) {
            while (r < s.length() && !isValid(cnt, target)) {
                cnt[s.charAt(r++) - 'A']--;
            }
            if (!isValid(cnt, target)) {
                break;
            }
            ans = Math.min(ans, r - l);
            cnt[s.charAt(l) - 'A']++;
        }
        return ans;
    }


    public static boolean isValid(int[] cnt, int target) {
        return cnt['Q' - 'A'] <= target && cnt['W' - 'A'] <= target &&
                cnt['E' - 'A'] <= target && cnt['R' - 'A'] <= target;
    }
}
