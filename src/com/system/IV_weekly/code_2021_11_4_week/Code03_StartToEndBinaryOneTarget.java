package com.system.IV_weekly.code_2021_11_4_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/11/25-8:50
 * @description [start, end]范围上的数字，有多少数字二进制中1的个数等于target
 * 限制：0 <= start <= end，0 <= target <= 64
 */
public class Code03_StartToEndBinaryOneTarget {

    // 暴力方法
    public static long nums1(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        long ans = 0;
        for (long i = start; i <= end; i++) {
            if (bitOne(i) == target) {
                ans++;
            }
        }
        return ans;
    }

    public static int bitOne(long num) {
        int bits = 0;
        for (int i = 63; i >= 0; i--) {
            if ((num & (1L << i)) != 0) {
                bits++;
            }
        }
        return bits;
    }

    /*
    ====================================================================================================================
     */

    public static long nums2(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        // 寻找end这数，最高位的1在哪？
        int eHigh = 62;
        while ((end & (1L << eHigh)) == 0) {
            eHigh--;
        }
        if (start == 0) {
            return process2(eHigh, 0, target, end);
        } else { // 170 ~ 3657 0 ~ 169 0 ~ 3657
            start--;
            int sHigh = 62;
            while (sHigh >= 0 && (start & (1L << sHigh)) == 0) {
                sHigh--;
            }
            return process2(eHigh, 0, target, end) - process2(sHigh, 0, target, start);
        }
    }

    // index..... 去做决定吧！
    // less == 1 之前做的决定 已经小于 num所对应的前缀状态了
    // less == 0 之前做的决定 等于 num所对应的前缀状态
    // 剩余几个1，需要出现！
    public static long process2(int index, int less, int rest, long num) {
        if (rest > index + 1) { // 剩余1的个数 > 剩下的位数
            return 0;
        }
        if (rest == 0) { // 已经不剩1了，之前的决定有效
            return 1L;
        }
        // 0 < rest <= index + 1
        // 还有1需要去消耗
        // 位数也够用
        if (less == 1) { // 之前做的决定 已经小于 num所对应的前缀状态了
            if (rest == index + 1) {
                return 1L;
            } else {
                // 后面剩余的位数 > 剩余1的数量
                // 某些位置填1，某些位置填0
                return process2(index - 1, 1, rest, num) + process2(index - 1, 1, rest - 1, num);
            }
        } else { // 之前做的决定 等于 num所对应的前缀状态
            if (rest == index + 1) { // 剩余的位数 == 剩余1的数量
                return (num & (1L << index)) == 0 ? 0 : process2(index - 1, 0, rest - 1, num);
            } else {
                // 剩余的位数 > 剩余1的数量
                if ((num & (1 << index)) == 0) {
                    return process2(index - 1, 0, rest, num);
                } else { // num 当前位置 1
                    // index 1 : less 0
                    // index 0 : less 1
                    return process2(index - 1, 1, rest, num) + process2(index - 1, 0, rest - 1, num);
                }
            }
        }
    }

    /*
    ====================================================================================================================
     */

    public static long nums3(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        int eHigh = 62;
        while ((end & (1L << eHigh)) == 0) {
            eHigh--;
        }
        long[][][] dpe = new long[eHigh + 1][2][target + 1];
        for (int i = 0; i <= eHigh; i++) {
            Arrays.fill(dpe[i][0], -1);
            Arrays.fill(dpe[i][1], -1);
        }
        long ansE = process3(eHigh, 0, target, end, dpe);
        if (start == 0) {
            return ansE;
        } else {
            start--;
            int sHigh = 62;
            while (sHigh >= 0 && (start & (1L << sHigh)) == 0) {
                sHigh--;
            }
            long[][][] dps = new long[sHigh + 1][2][target + 1];
            for (int i = 0; i <= sHigh; i++) {
                Arrays.fill(dps[i][0], -1);
                Arrays.fill(dps[i][1], -1);
            }
            long ansS = process3(sHigh, 0, target, start, dps);
            return ansE - ansS;
        }
    }

    public static long process3(int index, int less, int rest, long num, long[][][] dp) {
        if (rest > index + 1) { // 剩余1的个数 > 剩下的位数
            return 0;
        }
        if (rest == 0) { // 已经不剩1了，之前的决定有效
            return 1L;
        }
        if (dp[index][less][rest] != -1) {
            return dp[index][less][rest];
        }
        long ans = 0;
        if (less == 1) { // 之前做的决定 已经小于 num所对应的前缀状态了
            if (rest == index + 1) {
                ans = 1L;
            } else {
                // 后面剩余的位数 > 剩余1的数量
                // 某些位置填1，某些位置填0
                ans = process3(index - 1, 1, rest, num, dp) + process3(index - 1, 1, rest - 1, num, dp);
            }
        } else { // 之前做的决定 等于 num所对应的前缀状态
            if (rest == index + 1) { // 剩余的位数 == 剩余1的数量
                ans = (num & (1L << index)) == 0 ? 0 : process3(index - 1, 0, rest - 1, num, dp);
            } else {
                // 剩余的位数 > 剩余1的数量
                if ((num & (1 << index)) == 0) {
                    ans = process3(index - 1, 0, rest, num, dp);
                } else { // num 当前位置 1
                    // index 1 : less 0
                    // index 0 : less 1
                    ans = process3(index - 1, 1, rest, num, dp) + process3(index - 1, 0, rest - 1, num, dp);
                }
            }
        }
        dp[index][less][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        long range = 600L;
        System.out.println("功能测试开始");
        for (long start = 0L; start < range; start++) {
            for (long end = start; end < range; end++) {
                int target = (int) (Math.random() * 10);
                long ans1 = nums1(start, end, target);
                long ans2 = nums2(start, end, target);
                long ans3 = nums3(start, end, target);
                if (ans1 != ans2 || ans1 != ans3) {
                    System.out.println("出错了！");
                }
            }
        }
        System.out.println("功能测试结束");

        long start = 33281731L;
        long end = 204356810L;
        int target = 17;
        long startTime;
        long endTime;
        long ans1;
        long ans2;
        long ans3;

        System.out.println("大范围性能测试，开始");
        startTime = System.currentTimeMillis();
        ans1 = nums1(start, end, target);
        endTime = System.currentTimeMillis();
        System.out.println("方法一答案：" + ans1 + ", 运行时间(毫秒) : " + (endTime - startTime));
        startTime = System.currentTimeMillis();
        ans2 = nums2(start, end, target);
        endTime = System.currentTimeMillis();
        System.out.println("方法二答案：" + ans2 + ", 运行时间(毫秒) : " + (endTime - startTime));
        startTime = System.currentTimeMillis();
        ans3 = nums3(start, end, target);
        endTime = System.currentTimeMillis();
        System.out.println("方法三答案：" + ans3 + ", 运行时间(毫秒) : " + (endTime - startTime));
        System.out.println("大范围性能测试，结束");

        System.out.println("超大范围性能测试，开始");
        start = 88193819381L;
        end = 92371283713182371L;
        target = 30;
        startTime = System.currentTimeMillis();
        ans3 = nums3(start, end, target);
        endTime = System.currentTimeMillis();
        System.out.println("方法三答案：" + ans3 + ", 运行时间(毫秒) : " + (endTime - startTime));
        System.out.println("超大范围性能测试，结束");
    }
}
