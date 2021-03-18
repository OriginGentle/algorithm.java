package com.peter.day03;

import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/18-14:28
 * @Description 用栈结构实现队列结构
 */
public class TwoStacksImplementQueue {

    public static class twoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public twoStacksQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            // 前提:pop栈为空，push栈才能倒入，pop栈不为空，不能倒入
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int value) {
            stackPush.push(value);
            pushToPop();
        }

        public int poll() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
