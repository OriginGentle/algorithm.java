package com.system.D_weekly.code_2021_12_4_week;

import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2021/12/25-14:04
 * @description https://leetcode.com/problems/maximum-number-of-visible-points/
 */
public class Code04_MaximumNumberOfVisiblePoints {

    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int n = points.size();
        int a = location.get(0);
        int b = location.get(1);
        int zero = 0;
        double[] arr = new double[n << 1];
        int m = 0;
        for (int i = 0; i < n; i++) {
            int x = points.get(i).get(0) - a;
            int y = points.get(i).get(1) - b;
            if (x == 0 && y == 0) {
                zero++;
            } else {
                arr[m] = Math.toDegrees(Math.atan2(y, x));
                arr[m + 1] = arr[m] + 360;
                m += 2;
            }
        }
        Arrays.sort(arr, 0, m);
        int max = 0;
        for (int L = 0, R = 0; L < n; L++) {
            while (R < m && arr[R] - arr[L] <= angle) {
                R++;
            }
            max = Math.max(max, R - L);
        }
        return max + zero;
    }
}
