package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/1-08:38
 */
public class Problem_0233_NumberOfDigitOne {

    public static int countDigitOne(int num) {
        if (num < 1)
            return 0;

        int len = getNumOfLen(num);
        if (len == 1)
            return 1;

        int base = powerBaseOf10(len - 1);

        // 提取最高位的数字
        int first = num / base;

        int firstOneNum = (first == 1 ? num % base + 1 : base);
        int otherOneNum = first * (len - 1) * (base / 10);

        return firstOneNum + otherOneNum + countDigitOne(num % base);
    }

    private static int powerBaseOf10(int base) {

        return (int) Math.pow(10, base);
    }

    private static int getNumOfLen(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

}
