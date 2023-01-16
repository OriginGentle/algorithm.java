package com.system.II_basic.day17;

import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/17-15:32
 * @Description 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。如何实现?
 */
public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack == null) {
            return;
        }
        int i = process(stack);
        reverse(stack);
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int process(Stack<Integer> stack) {
        int result = stack.pop(); // 弹出栈顶元素
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = process(stack);
            stack.push(result);
            return last;
        }
    }

}
