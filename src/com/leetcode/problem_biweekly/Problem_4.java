package com.leetcode.problem_biweekly;

import java.util.HashSet;
import java.util.Set;

public class Problem_4 {

    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> set = new HashSet<>();
        int ans = 1;
        for (int num : rolls) {
            set.add(num);
            if (set.size() == k) {
                ans++;
                set.clear();
            }
        }
        return ans;
    }
}
