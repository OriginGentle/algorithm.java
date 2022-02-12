package com.weekly.code_2022_02_2_week;

/**
 * @author ycb
 * @date 2022/2/11-18:23
 * @description https://leetcode.com/problems/reaching-points/
 */
public class Code04_ReachingPoints {

    public static boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        while (tx != ty) {
            if (tx < ty) {
                ty -= tx;
            } else {
                tx -= ty;
            }
            if (sx == tx && sy == ty) {
                return true;
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        // 1) startX >= tx
        // 2) startY >= ty
        return (sx == tx && sy <= ty && (ty - sy) % sx == 0)
                || (sy == ty && sx <= tx && (tx - sx) % sy == 0);
    }
}
