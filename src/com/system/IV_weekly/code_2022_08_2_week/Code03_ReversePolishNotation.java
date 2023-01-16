package com.system.IV_weekly.code_2022_08_2_week;

import java.util.Stack;

/**
 * @author ycb
 * @date 2022/8/12-09:11
 * @desc 给定一个逆波兰式
 * 转化成正确的中序表达式
 * 要求只有必要加括号的地方才加括号
 */
public class Code03_ReversePolishNotation {

    public static int getAns(String rpn) {
        if (rpn == null || rpn.length() == 0) {
            return 0;
        }
        String[] part = rpn.split(" ");
        Stack<Integer> stack = new Stack<>();
        for (String str : part) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                int left = stack.pop();
                int right = stack.pop();
                int ans = 0;
                if (str.equals("+")) {
                    ans = left + right;
                } else if (str.equals("-")) {
                    ans = left - right;
                } else if (str.equals("*")) {
                    ans = left * right;
                } else {
                    ans = left / right;
                }
                stack.push(ans);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    /*
    ====================================================================================================================
     */

    enum Operation {
        SINGLE_NUMBER,
        ADD_OR_MINUS,
        MULTIPLY_OR_DIVIDE;
    }

    public static String convert(String rpn) {
        if (rpn == null || rpn.length() == 0) {
            return rpn;
        }
        String[] parts = rpn.split(" ");
        Stack<String> stackCmt = new Stack<>();
        Stack<Operation> stackOp = new Stack<>();
        for (String cur : parts) {
            if (cur.equals("+") || cur.equals("-")) {
                String b = stackCmt.pop();
                String a = stackCmt.pop();
                stackOp.pop();
                stackOp.pop();
                stackCmt.push(a + cur + b);
                stackOp.push(Operation.ADD_OR_MINUS);
            } else if (cur.equals("*") || cur.equals("/")) {
                String b = stackCmt.pop();
                String a = stackCmt.pop();
                Operation opB = stackOp.pop();
                Operation opA = stackOp.pop();
                String left = opA == Operation.ADD_OR_MINUS ? ("(" + a + ")") : a;
                String right = opB == Operation.ADD_OR_MINUS ? ("(" + b + ")") : b;
                stackCmt.push(left + cur + right);
                stackOp.push(Operation.MULTIPLY_OR_DIVIDE);
            } else {
                stackCmt.push(cur);
                stackOp.push(Operation.SINGLE_NUMBER);
            }
        }
        return stackCmt.pop();
    }

    // for test
    public static void main(String[] args) {
        // 3*(-5+13)+6/(2-3+2)-4*5*3
        String rpn = "3 -5 13 + * 6 2 3 - 2 + / + 4 5 3 * * -";
        System.out.println(getAns(rpn));
        System.out.println(convert(rpn));
    }
}
