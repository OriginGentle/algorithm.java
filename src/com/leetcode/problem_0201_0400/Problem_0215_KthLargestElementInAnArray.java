package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/29-09:31
 */
public class Problem_0215_KthLargestElementInAnArray {

    public static int findKthLargest1(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int process(int[] arr, int L, int R, int index) {
        if (L == R)
            return arr[L];

        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);

        if (index >= range[0] && index <= range[1])
            return arr[index];

        else if (index < range[0])
            return process(arr, L, range[0] - 1, index);

        else
            return process(arr, range[1] + 1, R, index);
    }

    /*
    ====================================================================================================================
     */

    public static int findKthLargest2(int[] nums, int k) {
        return bfprt(nums, 0, nums.length - 1, nums.length - k);
    }

    // arr[L ... R]
    // 如果排序的话，返回位于index位置的数
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R)
            return arr[L];

        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);

        if (index >= range[0] && index <= range[1])
            return arr[index];

        else if (index < range[0])
            return bfprt(arr, L, range[0] - 1, index);

        else
            return bfprt(arr, range[1] + 1, R, index);

    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1, more = R + 1;
        int cur = L;
        while (cur < more) {

            if (arr[cur] > pivot)
                swap(arr, cur, --more);

            else if (arr[cur] < pivot)
                swap(arr, ++less, cur++);

            else
                cur++;
        }
        return new int[]{less + 1, more - 1};
    }

    // arr[L...R] 每五个分一组
    // 每组内部排序，把中位数找出来，组成 mArr
    // 返回 mArr的中位数
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];

        for (int i = 0; i < mArr.length; i++) {
            int first = L + i * 5;
            mArr[i] = getMedian(arr, first, Math.min(R, first + 4));
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length >> 1);
    }

    private static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) >> 1];
    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
