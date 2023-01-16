package com.system.III_training.day34;

/**
 * @author ycb
 * @since 2021/10/22-9:24
 */
public class Problem_0340_LongestSubstringWithAtMostKDistinctCharacters {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] count = new int[256];
        int R = 0, ans = 0, diff = 0;
        for (int i = 0; i < N; i++) {
            // 窗口的右边界
            while (R < N && (diff < k || (diff == k && count[str[R]] > 0))) {
                diff += count[str[R]] == 0 ? 1 : 0;
                count[str[R++]]++;
            }
            // R 来到了第一个违规位置
            ans = Math.max(ans, R - i);
            diff -= count[str[i]] == 1 ? 1 : 0;
            count[str[i]]--;
        }
        return ans;
    }
}
