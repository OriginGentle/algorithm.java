package com.weekly.code_2022_03_2_week;

/**
 * @author ycb
 * @date 2022/3/12-18:24
 * @description 来自微软
 * 给定一个正数数组arr，长度为N，依次代表N个任务的难度，给定一个正数k
 * 你只能从0任务开始，依次处理到N-1号任务结束，就是一定要从左往右处理任务
 * 只不过，难度差距绝对值不超过k的任务，可以在一天之内都完成
 * 返回完成所有任务的最少天数
 */
public class Code06_JobMinDays {

    public static int minDay1(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + 1;
            int min = arr[i];
            int max = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);
                if (max - min <= k) {
                    dp[i] = Math.min(dp[i], 1 + (j - 1 >= 0 ? dp[j - 1] : 0));
                } else {
                    break;
                }
            }
        }
        return dp[n - 1];
    }

    /*
    ====================================================================================================================
     */

    public static int minDay2(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        int[] windowMax = new int[n];
        int[] windowMin = new int[n];
        int minL = 0, minR = 0, maxL = 0, maxR = 0, L = 0;
        for (int i = 0; i < n; i++) {
            while (maxL < maxR && arr[windowMax[maxR - 1]] <= arr[i]) {
                maxR--;
            }
            windowMax[maxR++] = i;
            while (minL < minR && arr[windowMin[minR - 1]] >= arr[i]) {
                minR--;
            }
            windowMin[minR++] = i;
            while (arr[windowMax[maxL]] - arr[windowMin[minL]] > k) {
                if (windowMax[maxL] == L) {
                    maxL++;
                }
                if (windowMin[minL] == L) {
                    minL++;
                }
                L++;
            }
            dp[i] = 1 + (L - 1 >= 0 ? dp[L - 1] : 0);
        }
        return dp[n - 1];
    }

    public static int[] randomArray(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) ((Math.random() + 1) * k);
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = 100;
        int v = 50000;
        int times = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int len = (int) ((Math.random() + 1) * n);
            int k = (int) (Math.random() * v);
            int[] arr = randomArray(len, k);
            int ans1 = minDay1(arr, k);
            int ans2 = minDay2(arr, k);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了");
                break;
            }
            System.out.println("测试结束");
        }
    }

}
