package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @since 2021/11/1-13:27
 */
public class Problem_575_DistributeCandies {

    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int type : candyType) {
            set.add(type);
        }
        return Math.min(set.size(), candyType.length >> 1);
    }
}
