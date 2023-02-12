package com.system.C_training.day52;

/**
 * @author ycb
 * @since 2021/11/14-18:50
 */
public class Problem_0683_KEmptySlots {

    public static int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] day = new int[n];
        for (int i = 0; i < n; i++) {
            day[bulbs[i] - 1] = i + 1;
        }
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for (int mid = 1; right < n; mid++) {
            if (day[mid] <= Math.max(day[left], day[right])) {
                if (mid == right) {
                    res = Math.min(res, Math.max(day[left], day[right]));
                }
                left = mid;
                right = mid + k + 1;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
