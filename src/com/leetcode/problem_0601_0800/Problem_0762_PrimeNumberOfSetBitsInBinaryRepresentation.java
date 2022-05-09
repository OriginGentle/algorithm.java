package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/4/5
 */
public class Problem_0762_PrimeNumberOfSetBitsInBinaryRepresentation {

    public static int countPrimeSetBits1(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int bits = bitCount(i);
            if (isPrimeNumber(bits)) {
                ans++;
            }
        }
        return ans;
    }

    public static int bitCount(int num) {
        int count = 0;
        while (num != 0) {
            count += (num & 1);
            num >>= 1;
        }
        return count;
    }

    public static boolean isPrimeNumber(int num) {
        if (num < 2) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        int half = num >> 1;
        for (int i = 2; i <= half; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    public int countPrimeSetBits2(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; ++x) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int left = 10;
        int right = 15;
        System.out.println(countPrimeSetBits1(left, right));

        System.out.println(bitCount(15));
    }

}
