package com.system.IV_weekly.code_2022_03_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/3/26
 * @desc 给定一个数组arr，可能有正、有负、有0，无序
 * 只能挑选两个数字，想尽量让两个数字加起来的绝对值尽量小
 * 返回可能的最小的值
 */
public class Code03_MinTowNumberSumABS {

    public static int minSumABS1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int n = arr.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.min(ans, Math.abs(arr[i] + arr[j]));
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minSumABS2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int split = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                split = i;
                break;
            }
        }
        // 全是非负整数
        if (split == 0) return arr[0] + arr[1];
        // 全是负数
        if (split == -1) return -arr[n - 2] - arr[n - 1];
        int ans = Integer.MAX_VALUE;
        if (split + 1 < n) ans = arr[split] + arr[split + 1];
        if (split - 2 >= 0) ans = Math.min(ans, -arr[split - 1] - arr[split - 2]);
        for (int i = 0; i < split; i++) {
            ans = Math.min(ans, Math.abs(arr[i] + near(arr, split, -arr[i])));
        }
        return ans;
    }

    private static int near(int[] arr, int start, int target) {
        int l = start;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) {
                r = mid - 1;
            } else {
                index = mid;
                l = mid + 1;
            }
        }
        if (index == -1) {
            return arr[start];
        } else {
            if (index == arr.length - 1) {
                return arr[arr.length - 1];
            } else {
                if (Math.abs(arr[index] - target) <= Math.abs(arr[index + 1] - target)) {
                    return arr[index];
                } else {
                    return arr[index + 1];
                }
            }
        }
    }

    /*
    ====================================================================================================================
     */

    public static int minSumABS3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int split = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                split = i;
                break;
            }
        }
        if (split == 0) {
            return arr[0] + arr[1];
        }
        if (split == -1) {
            return -arr[n - 2] - arr[n - 1];
        }
        int ans = Integer.MAX_VALUE;
        if (split + 1 < n) {
            ans = arr[split] + arr[split + 1];
        }
        if (split - 2 >= 0) {
            ans = Math.min(ans, -arr[split - 1] - arr[split - 2]);
        }
        int r = n - 1;
        for (int l = 0; l < split; l++) {
            ans = Math.min(ans, Math.abs(arr[l] + arr[r]));
            while (r - 1 >= split && Math.abs(arr[l] + arr[r]) >= Math.abs(arr[l] + arr[r - 1])) {
                ans = Math.min(ans, Math.abs(arr[l] + arr[--r]));
            }
        }
        return ans;
    }

    // for test
    public static int[] randomArray(int len, int val) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * val) - (int) (Math.random() * val);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 5000;
        int val = 100000;
        int times = 3000;
        System.out.println("测试开始：");
        for (int i = 0; i < times; i++) {
            int[] arr = randomArray(len, val);
            int ans1 = minSumABS1(arr);
            int ans2 = minSumABS2(arr);
            int ans3 = minSumABS3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束!");
    }
}
