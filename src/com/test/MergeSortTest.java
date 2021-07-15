package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/7/12-10:53
 */

// 归并排序
public class MergeSortTest {

    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L...R]上排有序
    public static void process(int[] arr, int L, int R) {
        // base case
        if (L == R) {
            return;
        }
        // 求中点位置
        int mid = L + ((R - L) >> 1);
        // 左部分排有序
        process(arr, L, mid);
        // 右部分排有序
        process(arr, mid + 1, R);
        // 左右部分合并
        merge(arr, L, mid, R);
    }

    // 合并左组和右组，做到整体有序
    public static void merge(int[] arr, int L, int M, int R) {
        // 准备一个辅助数组
        int[] help = new int[R - L + 1];
        int index = 0;
        // 左组的起始位置
        int p1 = L;
        // 右组的起始位置
        int p2 = M + 1;
        // 左组 右组合并
        while (p1 <= M && p2 <= R) {
            // 规定左组的数小于等于右组的数，copy左组的数
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 下面两个循环只会发生一个！
        // 从第一个while跳出的情况：p1先越界或p2先越界
        // p2先越界，左组还有数
        while (p1 <= M) {
            help[index++] = arr[p1++];
        }
        // p1先越界，右组还有数
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }
        // 把辅助数组copy回原数组
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }


    /*
    ====================================================================================================================
     */

    // 非递归方式实现
    public static void mergerSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            // 当前左组的位置
            int L = 0;
            while (L < N) {
                if (mergeSize > N - L) break;
                // 当前左组的最后位置:中心点
                int M = L + mergeSize - 1;
                // 当前右组的最后位置
                // 边界条件:右组不够步长时,取原数组最后位置
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                // 当前组合并完成之后，来到下个左组的起始位置
                L = R + 1;
            }
            // 1.防止mergeSize整型溢出的情况
            // 2.mergeSize > N/2:意味着当前的左组和右组合起来的总体长度已经超过了原数组长度
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{191, 89, 1, 76, 56, 30, -34, 90};
//        mergeSort1(arr);
        mergerSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
