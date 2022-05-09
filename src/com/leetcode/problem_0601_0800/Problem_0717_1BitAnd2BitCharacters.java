package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @since 2022/2/20-14:38
 */
public class Problem_0717_1BitAnd2BitCharacters {

    public static boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int index = 0;
        while (index < n - 1) {
            if (bits[index] == 0) {
                index++;
            } else {
                index += 2;
            }
        }
        return index == n - 1;
    }
}
