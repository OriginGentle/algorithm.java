package com.system.D_weekly.code_2023_06_3_week;

/**
 * @author ycb
 * @date 2023/6/25-08:35
 * @desc 来自字节
 * 给定整数数组arr，求删除任一元素后，
 * 新数组中长度为k的子数组累加和的最大值
 */
public class Code03_DeleteOneNumberLenKMaxSum {

    // 暴力方法
    public static int maxSum1(int[] arr, int k) {
        int n = arr.length;
        if (n <= k) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] rest = delete(arr, i);
            ans = Math.max(ans, lenKMaxSum(rest, k));
        }
        return ans;
    }

    public static int[] delete(int[] arr, int index) {
        int len = arr.length - 1;
        int[] ans = new int[len];
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != index) {
                ans[i++] = arr[j];
            }
        }
        return ans;
    }

    public static int lenKMaxSum(int[] arr, int k) {
        int n = arr.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            int cur = 0;
            for (int j = i, cnt = 0; cnt < k; j++, cnt++) {
                cur += arr[j];
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int maxSum2(int[] arr, int k) {
        int n = arr.length;
        if (n <= k) {
            return 0;
        }

        int[] window = new int[n];
        int l = 0, r = 0;
        long sum = 0;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            while (l < r && arr[window[r - 1]] >= arr[i]) {
                r--;
            }

            window[r++] = i;
            sum += arr[i];
            if (i >= k) {
                ans = Math.max(ans, (int) (sum - arr[window[l]]));
                if (window[l] == i - k) {
                    l++;
                }
                sum -= arr[i - k];
            }
        }

        return ans;
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * (2 * v + 1)) - v;
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int k = (int) (Math.random() * N) + 1;
            int ans1 = maxSum1(arr, k);
            int ans2 = maxSum2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
