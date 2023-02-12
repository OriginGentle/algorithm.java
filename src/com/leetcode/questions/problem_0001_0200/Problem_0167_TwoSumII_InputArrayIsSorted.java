package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/7/5-15:28
 */
public class Problem_0167_TwoSumII_InputArrayIsSorted {

    public static int[] twoSum1(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return new int[0];
        }
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int index = findTarget(arr, target - arr[i], i + 1, n - 1);
            if (index != -1) {
                return new int[]{i + 1, index + 1};
            }
        }
        return new int[0];
    }

    public static int findTarget(int[] arr, int target, int l, int r) {
        int ans = -1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (arr[m] == target) {
                ans = m;
                break;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int[] twoSum(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = {5, 75, 25};
        int target = 100;
        int[] ans = twoSum1(arr, target);
        for (int a : ans) {
            System.out.println(a);
        }
    }
}
