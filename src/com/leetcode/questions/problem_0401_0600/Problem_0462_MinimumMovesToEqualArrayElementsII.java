package com.leetcode.questions.problem_0401_0600;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2022/5/19-11:12
 */
public class Problem_0462_MinimumMovesToEqualArrayElementsII {

    public static int minMoves2_1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        Arrays.sort(nums);
        int mid = nums[n / 2];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums[i] - mid);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minMoves2_2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int kth = findKthNum(nums, 0, n - 1, n >> 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(nums[i] - kth);
        }
        return ans;
    }

    public static int findKthNum(int[] arr, int L, int R, int kth) {
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (kth >= range[0] && kth <= range[1]) {
                return arr[kth];
            } else if (kth < range[0]) {
                R = range[0] - 1;
            } else {
                L = range[1] + 1;
            }
        }
        return arr[L];
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] randomArray(int maxLen, int maxVal) {
        int[] arr = new int[(int) (Math.random() * maxLen + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxVal);
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxVal = 20000;
        int times = 10000;
        System.out.println("测试开始：");
        for (int i = 0; i < times; i++) {
            int[] arr1 = randomArray(maxLen, maxVal);
            int[] arr2 = copyArray(arr1);
            int ans1 = minMoves2_1(arr1);
            int ans2 = minMoves2_2(arr2);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束！");
    }
}
