package com.leetcode.questions.problem_0401_0600;

import java.util.Collections;
import java.util.List;

/**
 * @author ycb
 * @since 2022/1/18-9:39
 */
public class Problem_0539_MinimumTimeDifference {

    public static int findMinDifference1(List<String> timePoints) {
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int start = getMinutes(timePoints.get(0));
        int pre = start;
        for (int i = 1; i < timePoints.size(); i++) {
            int cur = getMinutes(timePoints.get(i));
            ans = Math.min(ans, cur - pre);
            pre = cur;
        }
        ans = Math.min(ans, start + 1440 - pre);
        return ans;
    }

    private static int getMinutes(String t) {
        String[] str = t.split(":");
        return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }

    /*
    ====================================================================================================================
     */

    // 鸽巢理论
    public static int findMinDifference2(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int start = getMinutes(timePoints.get(0));
        int pre = start;
        for (int i = 1; i < timePoints.size(); i++) {
            int cur = getMinutes(timePoints.get(i));
            ans = Math.min(ans, cur - pre);
            pre = cur;
        }
        ans = Math.min(ans, start + 1440 - pre);
        return ans;
    }
}
