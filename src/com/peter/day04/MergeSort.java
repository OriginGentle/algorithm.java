package com.peter.day04;

/**
 * @Author ycb
 * @Date 2021/3/18-15:29
 * @Description 归并排序
 */
public class MergeSort {
    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    // 左组和右组合并
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]; // 左组与右组相等，copy左组
        }
        // 要么p1越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        // 要么p2越界
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1; // 步长
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (mergeSize > N - L) break;
                int M = L + mergeSize - 1; // ?
                int R = M + Math.min(mergeSize, N - M - 1); // ?
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 7, 6, 9, 5};
        mergeSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
