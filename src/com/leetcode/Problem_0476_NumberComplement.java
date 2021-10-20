package com.leetcode;

/**
 * @author ycb
 * @since 2021/10/18-13:41
 */
public class Problem_0476_NumberComplement {

    public static int findComplement(int num) {
        int highBit = 0;
        // 获取高位的1在第几位
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highBit = i;
            } else {
                break;
            }
        }
        int mask = highBit == 30 ? Integer.MAX_VALUE : (1 << (highBit + 1)) - 1;
        return num ^ mask;
    }

    public static int func(int num) {
        int highbit = 0;
        // 获取高位的1在第几位
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }
        }
        return highbit;
    }

    public static void main(String[] args) {
        int num = 6;
        int highBit = func(num);
        System.out.println("highBit:" + highBit);
        System.out.println(1 << highBit);
    }
}
