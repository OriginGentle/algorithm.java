package com.test;

/**
 * @Author ycb
 * @Date 2021/7/4-8:40
 */

// 随机快排
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

    /*
    ====================================================================================================================
     */

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        // 小于区边界
        int less = L - 1;
        // 大于区边界
        int more = R;
        int index = L;
        // 当前位置，不能和大于区域的左边界重合
        while (index < more) {
            if (arr[index] < arr[R]) {
                // 命中情况①的处理
                swap(arr, index++, ++less);
            } else if (arr[index] == arr[R]) {
                // 命中情况②的处理
                index++;
            } else {
                // 命中情况③的处理
                swap(arr, index, --more);
            }
        }
        // 还剩下arr[R]位置的数未归位
        // arr[R]位置的数与当前大于区域的边界位置上的数进行交换
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    /*
    ====================================================================================================================
     */

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    // 可以考虑异或运算
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1};
        quickSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
