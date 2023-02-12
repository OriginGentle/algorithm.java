package com.leetcode.questions.problem_1001_1200;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/6/13-08:21
 */
public class Problem_1051_HeightChecker {

    public static int heightChecker1(int[] heights) {
        if (heights == null || heights.length <= 1) {
            return 0;
        }
        int n = heights.length;
        int[] sort = new int[n];
        for (int i = 0; i < n; i++) {
            sort[i] = heights[i];
        }
        Arrays.sort(sort);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (heights[i] != sort[i]) {
                ans++;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }

}
