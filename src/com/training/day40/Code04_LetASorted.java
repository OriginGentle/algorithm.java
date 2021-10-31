package com.training.day40;

/**
 * @author ycb
 * @date 2021/10/31-15:15
 * @description 给定两个数组A和B，长度都是N
 * A[i]不可以在A中和其他数交换，只可以选择和B[i]交换(0<=i<n)
 * 你的目的是让A有序，返回你能不能做到
 */
public class Code04_LetASorted {

    public static boolean letASorted(int[] A, int[] B) {
        return process(A, B, 0, Integer.MIN_VALUE);
    }

    // 当前推进到了i位置，对于A和B都是i位置
    // A[i]前一个数字，lastA
    // 能否通过题意中的操作，A[i] B[i] 让A整体有序
    public static boolean process(int[] A, int[] B, int index, int lastA) {
        if (index == A.length) {
            return true;
        }
        // 第一种选择:A[index] 不和 B[index] 交换
        if (A[index] >= lastA && process(A, B, index + 1, A[index])) {
            return true;
        }
        // 第二种选择:A[index] 不和 B[index] 交换
        if (B[index] >= lastA && process(A, B, index + 1, B[index])) {
            return true;
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean process2(int[] A, int[] B, int i, int lastA) {
        if (i == A.length) {
            return true;
        }
        // 第一种选择 : A[i]不和B[i]交换
        if (A[i] <= lastA && process2(A, B, i + 1, A[i])) {
            return true;
        }
        // 第一种选择 : A[i]和B[i]交换
        if (B[i] <= lastA && process2(A, B, i + 1, B[i])) {
            return true;
        }
        return false;
    }
}
