package com.test;

/**
 * @Author ycb
 * @Date 2021/7/4-8:40
 */
public class QuickSortTest {
    // 数组arr，在L到R位置上，以arr[R]作为划分值
    // <= arr[R] 放左边  > arr[R] 放右边
    // 返回 <=k区的边界
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        // 小于区域的起始位置
        int lessEqual = L - 1;
        // 当前位置的数
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                // 当前数和小于区域的下一个位置交换，当前数跳下一个
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        // 使用arr[R]位置数作为划分值
        // 当while循环结束时，只是L到R-1位置上的数归位
        // 还剩下R位置的数，要记得归位
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // 可以考虑异或运算
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 8, 3, 6};
        int index = partition(arr, 0, arr.length - 1);
        System.out.println(index);
    }
}
