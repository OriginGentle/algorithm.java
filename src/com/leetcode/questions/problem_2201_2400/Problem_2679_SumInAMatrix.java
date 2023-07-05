package com.leetcode.questions.problem_2201_2400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2023/7/4-10:42
 */
public class Problem_2679_SumInAMatrix {

    public static int matrixSum1(int[][] nums) {
        for (int[] num : nums) {
            Arrays.sort(num);
        }

        List<Integer> record = new ArrayList<>();
        int n = nums.length, m = nums[0].length, idx = 0;
        int ans = 0;
        while (idx < m) {

            for (int[] num : nums) {
                record.add(num[idx]);
            }

            record.sort((a, b) -> b - a);
            ans += record.get(0);
            record.clear();
            idx++;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int matrixSum2(int[][] nums) {
        int res = 0;
        int m = nums.length;
        int n = nums[0].length;
        for (int[] num : nums) {
            Arrays.sort(num);
        }

        for (int j = 0; j < n; j++) {
            int maxVal = 0;
            for (int[] num : nums) {
                maxVal = Math.max(maxVal, num[j]);
            }
            res += maxVal;
        }
        return res;
    }

}
