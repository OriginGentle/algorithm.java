package com.leetcode.questions.problem_2201_2400;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/2/24-11:28
 */
public class Problem_2357_MakeArrayZeroBySubtractingEqualAmounts {

    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }
}
