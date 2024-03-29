package com.system.D_weekly.code_2023_06_2_week;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2023/6/19-08:58
 * @desc 给你一个由 n 个数对组成的数对数组 pairs
 * 其中 pairs[i] = [lefti, righti] 且 lefti < righti 。
 * 现在，我们定义一种 跟随 关系，当且仅当 b < c 时
 * 数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面
 * 我们用这种形式来构造 数对链
 * 找出并返回能够形成的 最长数对链的长度
 * 你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 测试链接 : https://leetcode.cn/problems/maximum-length-of-pair-chain/
 */
public class Code04_MaximumLengthOfPairChain {

    public static int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int[] ends = new int[n];
        int size = 0;
        for (int[] pair : pairs) {

            int l = 0, r = size - 1;
            int find = -1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (ends[m] >= pair[0]) {
                    find = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }

            if (find == -1) {
                ends[size++] = pair[1];
            } else {
                ends[find] = Math.min(pair[1], ends[find]);
            }
        }
        return size;
    }
}
