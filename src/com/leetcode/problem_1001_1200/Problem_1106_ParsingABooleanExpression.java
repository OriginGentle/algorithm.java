package com.leetcode.problem_1001_1200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/11/5-22:48
 */
public class Problem_1106_ParsingABooleanExpression {

    public boolean parseBoolExpr1(String exp) {
        Deque<Character> cmt = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        for (char c : exp.toCharArray()) {
            if (c == ',')
                continue;

            if (c == 't' || c == 'f')
                cmt.addLast(c);

            if (c == '|' || c == '&' || c == '!')
                ops.addLast(c);

            if (c == '(')
                cmt.addLast('-');

            if (c == ')') {
                char op = ops.pollLast();
                char cur = ' ';

                while (!cmt.isEmpty() && cmt.peekLast() != '-') {
                    char top = cmt.pollLast();
                    cur = cur == ' ' ? top : calc(top, cur, op);
                }
                if (op == '!')
                    cur = cur == 't' ? 'f' : 't';

                cmt.pollLast();
                cmt.addLast(cur);
            }
        }
        return cmt.peekLast() == 't';
    }

    private char calc(char a, char b, char op) {
        boolean x = a == 't', y = b == 't';
        boolean ans = op == '|' ? x | y : x & y;
        return ans ? 't' : 'f';
    }

    /*
    ====================================================================================================================
     */

    public boolean parseBoolExpr2(String exp) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : exp.toCharArray()) {
            if (c == ',')
                continue;
            else if (c != ')') {
                stack.push(c);
            } else { // 当前遇到的是 ')'
                int tc = 0, fc = 0;
                while (!stack.isEmpty() && stack.peek() != '(') {
                    char cur = stack.pop();
                    if (cur == 't')
                        tc++;
                    else
                        fc++;
                }

                stack.pop();
                char op = stack.pop();

                switch (op) {
                    case '!':
                        stack.push(fc == 1 ? 't' : 'f');
                        break;
                    case '&':
                        stack.push(fc == 0 ? 't' : 'f');
                        break;
                    case '|':
                        stack.push(tc > 0 ? 't' : 'f');
                    default:
                        break;
                }
            }
        }
        return stack.pop() == 't';
    }
}
