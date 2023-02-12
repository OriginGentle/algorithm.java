package com.system.B_basic.day46;

import java.util.LinkedList;

/**
 * @author ycb
 * @date 2021/11/23-8:21
 * @description 给定一个数组arr，和一个正数M
 * 返回在子数组长度不大于M的情况下，最大的子数组累加和
 */
public class Code04_MaxSumLengthNoMore {

    // O(N^2)的解法，暴力解
    public static int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    // O(N) 窗口
    public static int maxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        // 前缀和数组
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int i = 0;
        int end = Math.min(N, M);
        for (; i < end; i++) {
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                qMax.pollLast();
            }
            qMax.add(i);
        }
        int max = sum[qMax.peekFirst()];
        int L = 0;
        for (; i < N; L++, i++) {
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                qMax.pollLast();
            }
            qMax.add(i);
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);
        }
        for (; L < N - 1; L++) {
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);
        }
        return max;
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
