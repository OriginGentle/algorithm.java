package com.system.III_training.day48;

/**
 * @author ycb
 * @since 2021/11/11-8:41
 */
public class Problem_0483_SmallestGoodBase {

    public String smallestGoodBase(String n) {
        Long num = Long.valueOf(n);
        //  num这个数，需要从m位开始试，固定位数，一定要有m位！
        for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
            // num开m次方
            long l = (long) (Math.pow(num, 1.0 / m));
            long r = (long) (Math.pow(num, 1.0 / (m - 1))) + 1L;
            while (l <= r) {
                long k = l + ((r - l) >> 1);
                long sum = 0L;
                long base = 1L;
                for (int i = 0; i < m && sum <= num; i++) {
                    sum += base;
                    base *= k;
                }
                if (sum < num) {
                    l = k + 1;
                } else if (sum > num) {
                    r = k - 1;
                } else {
                    return String.valueOf(k);
                }
            }
        }
        return String.valueOf(num - 1);
    }

    public static void main(String[] args) {
        int m = 8;
        int ans = (int) (Math.log(m + 1) / Math.log(2));
        System.out.println(ans);
    }
}
