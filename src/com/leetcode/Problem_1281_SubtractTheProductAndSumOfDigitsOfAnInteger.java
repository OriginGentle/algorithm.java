package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/18-8:52
 */
public class Problem_1281_SubtractTheProductAndSumOfDigitsOfAnInteger {

    public static int subtractProductAndSum(int n) {
        long p1 = 1, p2 = 0;
        while (n != 0) {
            int a = n % 10;
            p1 *= a;
            p2 += a;
            n /= 10;
        }
        return (int) (p1 - p2);
    }

    public static void main(String[] args) {
        int n = 234;
        int ans = subtractProductAndSum(n);
        System.out.println(ans);
    }
}
