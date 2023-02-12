package com.system.C_training.day20;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/10/3-11:41
 * @description 完美洗牌问题
 * 给定一个长度为偶数的数组arr，假设长度为N*2
 * 左部分：arr[L1……Ln]   右部分： arr[R1……Rn]
 * 请把arr调整成arr[L1,R1,L2,R2,L3,R3,…,Ln,Rn]
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Code03_ShuffleProblem {

    // 数组的长度为len，调整前的位置是i，返回调整之后的位置
    // 下标不从0开始，从1开始
    public static int modifyIndex1(int i, int len) {
        if (i <= len >> 1) {
            return 2 * i;
        } else {
            return 2 * (i - (len >> 1)) - 1;
        }
    }

    // 数组的长度为len，调整前的位置是i，返回调整之后的位置
    // 下标不从0开始，从1开始
    public static int modifyIndex(int i, int len) {
        return (2 * i) % (len + 1);
    }

    // 数组必须不为空，且长度为偶数
    // N : 数组长度，且必须为偶数，当N = (3 ^ k) - 1时，环的出发点时可以算出来的
    // 出发点分别为 1 ... (3 ^ k - 1)
    public static void shuffle(int[] arr) {
        if (arr == null && arr.length != 0 && (arr.length & 1) == 0) {
            shuffle(arr, 0, arr.length - 1);
        }
    }

    // 在arr[L..R]上做完美洗牌的调整（arr[L..R]范围上一定要是偶数个数字）
    public static void shuffle(int[] arr, int L, int R) {
        while (R - L + 1 > 0) { // 切成一块一块的解决，每一块的长度满足(3^k)-1
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            // 计算 <= len并且离len最近，满足(3^k) - 1的数
            // 也就是找到最大的k，满足3^k <= len+1
            while (base <= (len + 1) / 3) {
                base *= 3;
                k++;
            }
            // (3 ^ k) - 1
            // 当前要解决长度为base - 1的块，一半就是再除2
            int half = (base - 1) >> 1;
            // [L...R]的中点
            int mid = (L + R) / 2;
            // 要旋转的左部分为[L+half...mid], 右部分为arr[mid+1..mid+half]
            // 注意在这里，arr下标是从0开始的
            rotate(arr, L + half, mid, mid + half);
            // 旋转完成后，从L开始算起，长度为base-1的部分进行下标连续推
            cycles(arr, L, base - 1, k);
            L = L + base - 1;
        }
    }

    // 从start位置开始，往右长度为len长度的这一段，做下标连续推
    public static void cycles(int[] arr, int start, int len, int k) {
        // 找到每一个出发位置trigger，一共k个
        // 每一个trigger都进行下标连续推
        // 出发位置是从1开始算的，而数组下标是从0开始算的。
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preValue = arr[trigger + start - 1];
            int cur = modifyIndex(trigger, len);
            while (cur != trigger) {
                int tmp = arr[cur + start - 1];
                arr[cur + start - 1] = preValue;
                preValue = tmp;
                cur = modifyIndex(cur, len);
            }
            arr[cur + start - 1] = preValue;
        }
    }

    // [L...M]为左部分，[M+1...R]为右部分，左右两部分互换位置
    public static void rotate(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M + 1, R);
        reverse(arr, L, R);
    }

    // [L...R]做逆序调整
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
    }

    public static void wiggleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // 假设这个排序是额外空间复杂度O(1)的，当然系统提供的排序并不是，你可以自己实现一个堆排序
        Arrays.sort(arr);
        if ((arr.length & 1) == 1) {
            shuffle(arr, 1, arr.length - 1);
        } else {
            shuffle(arr, 0, arr.length - 1);
            for (int i = 0; i < arr.length; i += 2) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
            }
        }
    }

    // for test
    public static boolean isValidWiggle(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if ((i & 1) == 1 && arr[i] < arr[i - 1]) {
                return false;
            }
            if ((i & 1) == 0 && arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray() {
        int len = (int) (Math.random() * 10) * 2;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000000; i++) {
            int[] arr = generateArray();
            wiggleSort(arr);
            if (!isValidWiggle(arr)) {
                System.out.println("Oops!");
                printArray(arr);
                break;
            }
        }
    }
}
