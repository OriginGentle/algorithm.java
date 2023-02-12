package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @since 2022/1/10-17:52
 */
public class Problem_0306_AdditiveNumber {

    public static boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }
        return dfs(num, 0, 0, 0, 0);
    }

    // 从index开始做选择，已经选了count个数，
    // 前一个数是prev，前一个的前一个数prevPrev
    // 返回当前字符串是不是累加数
    public static boolean dfs(String str, int index, int count, long prevPrev, long prev) {
        if (index >= str.length()) {
            return count > 2;
        }
        long curAns = 0;
        // 从index...i的位置组成的数字，作为此时的选择
        // 组成的每一个数字都去试一遍
        for (int i = index; i < str.length(); i++) {
            char c = str.charAt(i);
            // 前导不能为0，但是自己可以单独作为0进行使用
            if (str.charAt(index) == '0' && i > index) {
                return false;
            }
            curAns = curAns * 10 + c - '0';
            if (count >= 2) {
                long sum = prevPrev + prev;
                if (curAns > sum) {
                    return false;
                }
                if (curAns < sum) {
                    continue;
                }
            }
            if (dfs(str, i + 1, count + 1, prev, curAns)) {
                return true;
            }
        }
        return false;
    }
}
