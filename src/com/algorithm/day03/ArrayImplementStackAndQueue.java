package com.algorithm.day03;

/**
 * @Author ycb
 * @Date 2021/3/12-16:13
 * @Description 数组实现栈和队列
 */
public class ArrayImplementStackAndQueue {
    // 数组实现栈
    public static class MyStack {
        private Object[] stack;
        // 栈内元素数量
        private int size;
        // 栈顶标记
        private int top = -1;
        private static final int DEFAULT_LENGTH = 16;

        private MyStack() {
            this.size = 0;
            stack = new Object[DEFAULT_LENGTH];
        }

        private MyStack(int len) {
            this.size = len;
            stack = new Object[len];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == stack.length;
        }

        public void push(Object value) {
            if (!isFull()) {
                stack[++top] = value;
                size++;
            } else {
                throw new RuntimeException("栈已满");
            }
        }

        public Object pop() {
            if (!isEmpty()) {
                size--;
                return stack[top--];
            } else {
                return null;
            }
        }

        public Object getTop() {
            if (!isEmpty()) {
                return stack[top];
            } else {
                return null;
            }
        }
    }

    // 数组实现队列
    public static class MyQueue {
        private Object[] queue;
        private int size;
        private int head;
        private int tail;
        private static final int DEFAULT_LENGTH = 16;

        public MyQueue() {
            queue = new Object[DEFAULT_LENGTH];
            size = 0;
            head = 0;
            tail = 0;
        }

        public MyQueue(int len) {
            queue = new Object[len];
            size = 0;
            head = 0;
            tail = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }

        public void add(int value) {
            if (!isFull()) {
                queue[tail++] = value;
                size++;
            } else {
                throw new RuntimeException("队列已满");
            }
        }

        public Object poll() {
            if (!isEmpty()) {
                size--;
                while (tail != head){
                    return queue[head++];
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }

        System.out.println("=======================");

        MyQueue queue = new MyQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(5);
        queue.add(7);
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }
}

