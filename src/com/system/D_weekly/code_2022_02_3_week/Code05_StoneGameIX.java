package com.system.D_weekly.code_2022_02_3_week;

/**
 * @author ycb
 * @date 2022/2/19-21:14
 * @description https://leetcode.com/problems/stone-game-ix/
 */
public class Code05_StoneGameIX {

    public static boolean stoneGameIX(int[] stones) {
        int[] counts = new int[3];
        for (int stone : stones) {
            counts[stone % 3]++;
        }
        return counts[0] % 2 == 0 ? counts[1] != 0 && counts[2] != 0
                : Math.abs(counts[1] - counts[2]) > 2;
    }
}
