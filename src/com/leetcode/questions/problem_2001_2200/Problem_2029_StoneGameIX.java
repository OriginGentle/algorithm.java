package com.leetcode.questions.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/1/20-8:22
 */
public class Problem_2029_StoneGameIX {

    public static boolean stoneGameIX(int[] stones) {
        int[] tmp = new int[3];
        for (int i : stones) {
            tmp[i % 3]++;
        }
        return tmp[0] % 2 == 0 ? !(tmp[1] == 0 || tmp[2] == 0) : !(Math.abs(tmp[1] - tmp[2]) <= 2);
    }
}
