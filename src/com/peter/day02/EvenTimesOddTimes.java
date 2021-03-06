package com.peter.day02;

/**
 * @Author ycb
 * @Date 2021/3/5-10:54
 */
// 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
public class EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            // 整个数组异或得到的值为:出现奇数次的number的异或值
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // 公式 :     N&((~N)+1)
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static int bit1Counts(int N) {
        int count = 0;
        while (N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne;
        }
        return count;
    }
}
