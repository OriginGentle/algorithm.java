package com.system.D_weekly.code_2023_03_3_week;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/3/16-10:52
 * @desc 大厂笔试题
 * 给定一个数组arr，长度为n
 * 再给定一个数字k，表示一定要将arr划分成k个集合
 * 每个数字只能进一个集合
 * 返回每个集合内部的平均值都累加起来最小的值
 * 平均值向下取整
 * 1 <= n <= 10^5
 * 0 <= arr[i] <= 10^5
 * 1 <= k <= n
 */
public class Code02_SplitToSubsetMakeMinAverageSum {

    // 暴力方法
    public static int minAverageSum1(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        ArrayList<Info> sets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            sets.add(new Info(0, 0));
        }
        return process(arr, 0, k, sets);
    }

    public static class Info {
        public int sum;
        public int cnt;

        public Info(int s, int c) {
            sum = s;
            cnt = c;
        }
    }

    // 暴力方法
    public static int process(int[] arr, int i, int k, ArrayList<Info> sets) {
        int ans;
        if (i == arr.length) {
            ans = 0;
            for (Info set : sets) {
                if (set.cnt == 0) {
                    return Integer.MAX_VALUE;
                } else {
                    ans += set.sum / set.cnt;
                }
            }
        } else {
            ans = Integer.MAX_VALUE;
            int cur = arr[i];
            for (int j = 0; j < k; j++) {
                sets.get(j).sum += cur;
                sets.get(j).cnt += 1;
                ans = Math.min(ans, process(arr, i + 1, k, sets));
                sets.get(j).sum -= cur;
                sets.get(j).cnt -= 1;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minAverageSum2(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        Arrays.sort(arr);
        int ans = 0, sum = 0;
        for (int i = 0; i < k - 1; i++) {
            ans += arr[i];
        }

        for (int i = k - 1; i < arr.length; i++) {
            sum += arr[i];
        }

        ans += sum / (arr.length - k + 1);
        return ans;
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 8;
        int V = 10000;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int k = (int) (Math.random() * n) + 1;
            int ans1 = minAverageSum1(arr, k);
            int ans2 = minAverageSum2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
