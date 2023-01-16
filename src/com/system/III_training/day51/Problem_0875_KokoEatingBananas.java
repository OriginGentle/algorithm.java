package com.system.III_training.day51;

/**
 * @author ycb
 * @since 2021/11/14-14:06
 */
public class Problem_0875_KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {
        int L = 1;
        // 抓数组中的最大值
        // 吃香蕉的最大速度
        int R = 0;
        for (int p : piles) {
            R = Math.max(p, R);
        }
        int ans = 0;
        int M = 0;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (hours(piles, M) <= h) {
                ans = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return ans;
    }

    // 所有的香蕉都在piles中
    // 每小时吃香蕉的速度是speed
    // 返回吃完这些香蕉需要多少小时
    public static int hours(int[] piles, int speed) {
        int ans = 0;
        int offset = speed - 1;
        for (int p : piles) {
            ans += (p + offset) / speed;
        }
        return ans;
    }
}
