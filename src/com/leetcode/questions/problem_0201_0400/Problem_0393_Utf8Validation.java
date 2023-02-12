package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/3/13-12:37
 */
public class Problem_0393_Utf8Validation {

    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int cur = data[i], j = 7;
            while (j >= 0 && (((cur >> j) & 1) == 1)) {
                j--;
            }
            int cnt = 7 - j;
            if (cnt == 1 || cnt > 4) {
                return false;
            }
            if (i + cnt - 1 >= n) {
                return false;
            }
            for (int k = i + 1; k < i + cnt; k++) {
                if (((data[k] >> 7) == 1) && (((data[k] >> 6) & 1) == 0)) continue;
                return false;
            }
            if (cnt == 0) {
                i++;
            } else {
                i += cnt;
            }
        }
        return true;
    }
}
