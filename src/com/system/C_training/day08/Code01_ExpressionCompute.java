package com.system.C_training.day08;

import java.util.LinkedList;

/**
 * @author ycb
 * @date 2021/8/20-14:19
 * @description https://leetcode.com/problems/basic-calculator-iii/
 */
public class Code01_ExpressionCompute {

    public static int calculate(String str) {
        return func(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] func(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] ans = new int[2];
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') { // 遇到的是数字
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到的是左括号
                ans = func(str, i + 1);
                cur = ans[0];
                i = ans[1] + 1;
            }
        }
        addNum(queue, cur);
        return new int[]{getNum(queue), i};
    }

    public static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            int cur = 0;
            String top = queue.pollLast();
            if (top.equals("+") || top.equals("-")) {
                queue.addLast(top);
            } else {
                cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "2*3+((9-2)*4)+(8-7)/5";
        int ans = calculate(str);
        System.out.println(ans);
    }

}
