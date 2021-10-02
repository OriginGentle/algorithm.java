package com.training.day19;

/**
 * @author ycb
 * @date 2021/9/27-11:37
 * @description 给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 可以数出1这个字符出现了6次
 * 给定一个正数N，如果把1~N都列出来，
 * 返回1这个字符出现的多少次
 */
public class Code03_OneNumber {

    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += getOneNums(i);
        }
        return count;
    }

    public static int getOneNums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }

    /*
    ====================================================================================================================
     */

    // 数位dp
    // 1 ~ num 这个范围上有多少个1
    public static int solution2(int num) {
        if (num < 1) {
            return 0;
        }
        // num -> 14567
        // len = 5位
        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        // num   897635617
        // temp1 100000000
        int temp1 = powerBaseOf10(len - 1);
        // 获取num的最高位 num / temp1
        int first = num / temp1;
        // 最高位是1 : num % temp1 + 1
        // 最高位是first: temp1;
        int firstOneNum = first == 1 ? num % temp1 + 1 : temp1;
        // 除去最高位是1之外
        // 最高位是1 :  10(k-2次方) * (k-1) * 1
        // 最高位是first : 10(k-2次方) * (k-1) * first
        int otherOneNum = first * (len - 1) * (temp1 / 10);
        return firstOneNum + otherOneNum + solution2(num % temp1);
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static void main(String[] args) {
        int num = 50000000;
        long start1 = System.currentTimeMillis();
        System.out.println(solution1(num));
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(solution2(num));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + " ms");

    }
}
