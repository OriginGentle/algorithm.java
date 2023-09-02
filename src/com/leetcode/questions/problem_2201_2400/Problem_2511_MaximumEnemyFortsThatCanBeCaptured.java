package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/9/2-00:46
 */
public class Problem_2511_MaximumEnemyFortsThatCanBeCaptured {

    public static int captureForts(int[] forts) {
        int n = forts.length;
        int ans = 0, pre = -1;
        for (int i = 0; i < n; i++) {
            if (forts[i] == 1 || forts[i] == -1) {
                if (pre >= 0 && forts[i] != forts[pre]) {
                    ans = Math.max(ans, i - pre - 1);
                }
                pre = i;
            }
        }
        return ans;
    }

}
