package com.leetcode.questions.problem_1201_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2023/2/23-10:01
 */
public class Problem_1238_CircularPermutationInBinaryRepresentation {

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add((i >> 1) ^ i ^ start);
        }
        return ans;
    }
}
