package com.peter.day17;

import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/17-15:32
 * @Description 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。如何实现?
 */
public class ReverseStackUsingRecursive {
    public static void reverse(Stack<Integer> stack) {
        if (stack == null) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}
