package com.system.C_training.day31;

import java.util.Stack;

/**
 * @author ycb
 * @since 2021/10/17-21:24
 */
public class Problem_0150_EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                compute(stack, str);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.peek();
    }

    public static void compute(Stack<Integer> stack, String op) {
        Integer num2 = stack.pop();
        Integer num1 = stack.pop();
        int ans = 0;
        switch (op) {
            case "+":
                ans = num1 + num2;
                break;
            case "-":
                ans = num1 - num2;
                break;
            case "*":
                ans = num1 * num2;
                break;
            case "/":
                ans = num1 / num2;
                break;
        }
        stack.push(ans);
    }
}
