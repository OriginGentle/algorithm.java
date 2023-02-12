package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/11/3-08:42
 */
public class Problem_1668_MaximumRepeatingSubstring {

    public static int maxRepeating(String sequence, String word) {
        int sLen = sequence.length();
        int wLen = word.length();
        if (wLen > sLen)
            return 0;

        if (sequence.equals(word))
            return 1;

        int count = 0, maxCount = 0, L = 0;
        boolean flag = false;
        while (L <= sLen - wLen) {
            if (sequence.substring(L, L + wLen).equals(word)) {
                count++;
                L += wLen;
                flag = true;
            } else {
                if (flag) {
                    L = L - wLen + 1;
                    flag = false;
                } else {
                    L++;
                }
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
}
