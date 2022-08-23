package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/23-11:11
 */
public class Problem_0201_BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd1(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    /*
    ====================================================================================================================
     */

    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }
}
