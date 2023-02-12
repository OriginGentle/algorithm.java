package com.leetcode.questions.problem_1601_1800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/12/18-19:17
 */
public class Problem_1703_MinimumAdjacentSwapsForKConsecutiveOnes {

    public int minMoves(int[] nums, int k) {
        List<Integer> cmt = new ArrayList<>();
        List<Integer> preSum = new ArrayList<>();
        preSum.add(0);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cmt.add(i - cmt.size());
                preSum.add(preSum.get(preSum.size() - 1) + cmt.get(cmt.size() - 1));
            }
        }

        int m = cmt.size(), res = Integer.MAX_VALUE;
        for (int i = 0; i <= m - k; i++) {
            int mid = i + k / 2;
            int right = cmt.get(mid);
            res = Math.min(
                    res,
                    (1 - k % 2) * right +
                            (preSum.get(i + k) - preSum.get(mid + 1)) -
                            (preSum.get(mid) - preSum.get(i))
            );
        }
        return res;
    }
}
