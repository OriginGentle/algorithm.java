package com.leetcode.problem_1001_1200;

/**
 * @author ycb
 * @date 2022/6/17-08:10
 */
public class Problem_1089_DuplicateZeros {

    public static void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length, top = 0, i = -1;
        while (top < n) {
            i++;
            top += arr[i] == 0 ? 2 : 1;
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j--] = 0;
            i--;
        }
        while (j >= 0) {
            arr[j--] = arr[i];
            if (arr[i] == 0) {
                arr[j--] = arr[i];
            }
            i--;
        }
    }
}
