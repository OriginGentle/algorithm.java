package com.leetcode;

/**
 * @author ycb
 * @since 2022/1/9-14:27
 */
public class Problem_1629_SlowestKey {

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        char ans = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            char ch = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > maxTime || (time == maxTime && ch > ans)) {
                ans = ch;
                maxTime = time;
            }
        }
        return ans;
    }
}
