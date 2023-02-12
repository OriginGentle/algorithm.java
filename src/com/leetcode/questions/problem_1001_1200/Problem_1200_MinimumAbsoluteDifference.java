package com.leetcode.questions.problem_1001_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @date 2022/7/4-08:26
 */
public class Problem_1200_MinimumAbsoluteDifference {

    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int n = arr.length;
        Arrays.sort(arr);

        for (int i = 1; i < n; i++) {
            int cur = arr[i] - arr[i - 1];
            if (cur < min) {
                min = cur;
                ans.clear();
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ans.add(list);

            } else if (cur == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ans.add(list);
            }
        }
        return ans;
    }
}
