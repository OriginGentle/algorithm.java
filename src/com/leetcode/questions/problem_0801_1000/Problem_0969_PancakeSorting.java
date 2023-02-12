package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/2/19-21:52
 */
public class Problem_0969_PancakeSorting {

    public static List<Integer> pancakeSort(int[] arr) {
        List<Integer> ans = new ArrayList<>();
        for (int i = arr.length; i > 1; i--) {
            int index = 0;
            for (int j = 1; j < i; j++) {
                if (arr[j] >= arr[index]) {
                    index = j;
                }
            }
            if (index == i - 1) continue;
            reverse(arr, index);
            reverse(arr, i - 1);
            ans.add(index + 1);
            ans.add(i);
        }
        return ans;
    }

    public static void reverse(int[] arr, int end) {
        int l = 0;
        int r = end;
        while (l < r) {
            int tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
    }
}
