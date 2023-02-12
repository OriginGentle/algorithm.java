package com.system.C_training.day03;

import java.util.Arrays;

/**
 * @Author ycb
 * @Date 2021/7/29-11:15
 * @Description https://leetcode.com/problems/closest-subsequence-sum/
 */
public class Code06_ClosestSubsequenceSum {

    public static int[] l = new int[1 << 20];
    public static int[] r = new int[1 << 20];

    // 1 <= nums.length <= 40
    // -10^7 <= nums[i] <= 10^7
    // -10^9 <= goal <= 10^9
    // 数据量过大,动态规划不可行,采用分治方法
    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int le = process(nums, 0, nums.length >> 1, 0, 0, l);
        int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int ans = Math.abs(goal);
        // 答案可能只需要左边的数构成
        // 答案可能只需要右边的数构成
        // 答案可能需要左边和右边的数构成
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            // 不要index位置的数
            fill = process(nums, index + 1, end, sum, fill, arr);
            // 要index位置的数
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        return fill;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(1 << 3);
        int[] left = new int[1 << 3];
        int[] right = new int[1 << 3];
        int N = arr.length;

        int li = process(arr, 0, N >> 1, 0, 0, left);
        System.out.println("li=" + li);
        int ri = process(arr, N >> 1, N, 0, 0, right);
        System.out.println("ri=" + ri);
        for (int i = 0; i < left.length; i++) {
            System.out.print(left[i] + " ");
        }
        System.out.println();
        System.out.println("===============================");
        for (int i = 0; i < left.length; i++) {
            System.out.print(right[i] + " ");
        }
    }

}
