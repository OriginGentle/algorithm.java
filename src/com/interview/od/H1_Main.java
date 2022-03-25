package com.interview.od;

import java.util.Arrays;

public class H1_Main {

    public static void sortTime(int[][] arr) {
        Arrays.sort(arr, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ?
                a[1] - b[1] : a[2] != a[2] ? a[2] - b[2] : a[3] - b[3]);
    }


    public static int[][] randomArray(int xLen, int yLen, int val) {
        int[][] arr = new int[xLen][yLen];
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                arr[i][j] = (int) (Math.random() * val) + 1;
            }
        }
        return arr;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }

    public static void main(String[] args) {
        int xLen = 4;
        int yLen = 4;
        int val = 20;
        int times = 1;
        for (int ri = 0; ri < times; ri++) {
            int[][] arr = randomArray(xLen, yLen, val);
            sortTime(arr);
            printArray(arr);
        }
    }
}
