package com.leetcode.problem_0201_0400;

import java.util.LinkedList;

/**
 * @author ycb
 * @date 2022/8/30-09:00
 */
public class Problem_0224_BasicCalculator {

    public static int calculate(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    public static int[] process(char[] str, int i) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        while (i < str.length && str[i] != ')') {

            if (str[i] >= '0' && str[i] <= '9')  // 当前遇到的是数字
                cur = cur * 10 + str[i++] - '0';

            else if (str[i] == ' ')
                i++;

            else if (str[i] != '(') { // 遇到的是运算符
                addNum(queue, cur);
                queue.addLast(String.valueOf(str[i++]));
                cur = 0;

            } else {
                int[] ans = process(str, i + 1);
                cur = ans[0];
                i = ans[1] + 1;
            }
        }

        addNum(queue, cur);

        return new int[]{getNum(queue), i};
    }

    private static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        String cur;
        int num;
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

    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String top = queue.pollLast();

            if ("+".equals(top) || "-".equals(top))
                queue.addLast(top);

            else {
                int cur = Integer.valueOf(queue.pollLast());
                num = "*".equals(top) ? cur * num : cur / num;
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static void main(String[] args) {
        String s = "1 + 1";
        int ans = calculate(s);
        System.out.println(ans);
    }
}
