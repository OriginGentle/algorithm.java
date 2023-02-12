package com.system.D_weekly.code_2022_03_3_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/3/19-19:07
 * @description 来自美团
 * 给定一个数组arr，你可以随意挑选其中的数字
 * 但是你挑选的数中，任何两个数a和b，必须Math.abs(a - b) > 1
 * 返回你最多能挑选几个数
 */
public class Code01_LongestUncontinuousSet {

    public static int longestUncontinuous1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process(arr, 0, new int[arr.length], 0);
    }

    public static int process(int[] arr, int i, int[] path, int j) {
        if (i == arr.length) {
            for (int k = 1; k < j; k++) {
                if (path[k - 1] + 1 >= path[k]) {
                    return 0;
                }
            }
            return j;
        } else {
            int p1 = process(arr, i + 1, path, j);
            path[j] = arr[i];
            int p2 = process(arr, i + 1, path, j + 1);
            return Math.max(p1, p2);
        }
    }

    /*
    ====================================================================================================================
     */

    public static int longestUncontinuous2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int size = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[size - 1]) {
                arr[size++] = arr[i];
            }
        }
        int[] dp = new int[size];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < size; i++) {
            dp[i] = 1;
            if (arr[i] - arr[i - 1] > 1) {
                dp[i] = dp[i - 1] + 1;
            }
            if (i - 2 >= 0 && arr[i] - arr[i - 2] > 1) {
                dp[i] = Math.max(dp[i - 2] + 1, dp[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int[] randomArray(int len, int val) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * val);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int val = 100;
        int times = 1000;
        System.out.println("测试开始：");
        for (int i = 0; i < times; i++) {
            int[] arr = randomArray(len, val);
            int ans1 = longestUncontinuous1(arr);
            int ans2 = longestUncontinuous2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oop,出错了");
                break;
            }
        }
        System.out.println("测试结束！");
    }
}
