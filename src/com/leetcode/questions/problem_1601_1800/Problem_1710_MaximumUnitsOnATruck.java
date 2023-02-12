package com.leetcode.questions.problem_1601_1800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/11/15-09:09
 */
public class Problem_1710_MaximumUnitsOnATruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (int[] boxType : boxTypes) {
            int boxNum = boxType[0], unitNum = boxType[1];
            if (boxNum < truckSize) {
                ans += boxNum * unitNum;
                truckSize -= boxNum;
            } else {
                ans += truckSize * unitNum;
                break;
            }
        }
        return ans;
    }
}
