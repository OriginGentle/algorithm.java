package com.system.C_training.day33;

/**
 * @author ycb
 * @since 2021/10/21-8:34
 */
public class Problem_0277_FindTheCelebrity {

    public static boolean knows(int x, int i) {
        return true;
    }

    public int findCelebrity(int n) {
        // cand:明星候选人
        int cand = 0;
        for (int i = 0; i < n; ++i) {
            if (knows(cand, i)) {
                cand = i;
            }
        }
        // 1) cand是不是不认识所有的人 cand...（右侧cand都不认识）
        for (int i = 0; i < cand; ++i) {
            if (knows(cand, i)) {
                return -1;
            }
        }
        // 2) 是不是所有的人都认识cand
        for (int i = 0; i < n; ++i) {
            if (!knows(i, cand)) {
                return -1;
            }
        }
        return cand;
    }
}
