package com.training.day32;

/**
 * @author ycb
 * @since 2021/10/19-8:20
 */
public class Problem_0191_NumberOf1Bits {

    public static int hammingWeight1(int n) {
        int ans = 0;
        int rightOne = 0;
        while (n != 0) {
            rightOne = n & (-n);
            ans++;
            n ^= rightOne;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int hammingWeight2(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        for (int i = 0; i < 1000; i++) {
            if (hammingWeight1(i) != hammingWeight2(i)) {
                System.out.println("Oops");
                System.out.println(hammingWeight1(i));
                System.out.println(hammingWeight2(i));
                break;
            }
        }
        System.out.println("测试结束");
    }
}
