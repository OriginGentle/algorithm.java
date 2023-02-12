package com.leetcode.questions.problem_1601_1800;

/**
 * @author ycb
 * @date 2022/12/2-20:35
 */
public class Problem_1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox {

    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int cmt = 0;
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1')
                    cmt += Math.abs(j - i);
            }
            ans[i] = cmt;
        }
        return ans;
    }
}
