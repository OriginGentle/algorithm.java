package com.system.IV_weekly.code_2022_03_2_week;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/3/12-18:42
 * @description 来自谷歌
 * 给定一个数组arr，长度为n
 * 表示n个服务员，每个人服务一个人的时间
 * 给定一个正数m，表示有m个人等位
 * 如果你是刚来的人，请问你需要等多久？
 * 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
 */
public class Code07_MinWaitingTime {

    public static int minWaitingTime1(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < arr.length; i++) {
            heap.add(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();
            cur[0] += cur[1];
            heap.add(cur);
        }
        return heap.peek()[0];
    }

    /*
    ====================================================================================================================
     */

    public static int minWaitingTime2(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int best = Integer.MAX_VALUE;
        for (int num : arr) {
            best = Math.min(num, best);
        }
        int L = 0;
        int R = best * m;
        int mid = 0;
        int near = 0;
        while (L < R) {
            mid = (L + R) >> 1;
            int caver = 0;
            for (int num : arr) {
                caver += (mid / num) + 1;
            }
            if (caver >= m + 1) {
                near = caver;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return near;
    }

    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int n = 1000;
        int v = 2000;
        int times = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = randomArray(len, v);
            int m = (int) ((Math.random() * v) + 1) * 10;
            int ans1 = minWaitingTime1(arr, m);
            int ans2 = minWaitingTime2(arr, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oop");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
