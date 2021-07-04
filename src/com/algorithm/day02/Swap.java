package com.algorithm.day02;

/**
 * @Author ycb
 * @Date 2021/3/5-10:41
 * @Description 异或运算：(1)0^N == N  N^N == 0 (2)异或运算满足交换律和结合率 (3)和1做异或得到原值的相反值
 */
public class Swap {
    public static void swap(int i, int j) {
        i = i ^ j;
        j = i ^ j; // i^i^j
        i = i ^ j; // i^j^i^i^j
        System.out.print(i + " " + j);
    }

    public static void main(String[] args) {
        int a = 12;
        int b = 58;
        swap(12, 58);
    }

    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j] ^ arr[j];
        arr[j] = arr[i] ^ arr[j] ^ arr[j];
    }
}
