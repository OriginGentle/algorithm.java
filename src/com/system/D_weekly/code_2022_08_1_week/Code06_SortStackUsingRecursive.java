package com.system.D_weekly.code_2022_08_1_week;

import java.util.Stack;

/**
 * @author ycb
 * @date 2022/8/5-11:12
 * @desc 栈只提供push、pop、isEmpty三个方法
 * 请完成无序栈的排序，要求排完序之后，从栈顶到栈底从小到大
 * 只能使用栈提供的push、pop、isEmpty三个方法、以及递归函数
 * 除此之外不能使用任何的容器，任何容器都不许，连数组也不行
 * 也不能自己定义任何结构体
 * 就是只用：
 * 1) 栈提供的push、pop、isEmpty三个方法
 * 2) 简单返回值的递归函数
 */
public class Code06_SortStackUsingRecursive {

    public static void sortStack(Stack<Integer> stack) {
        int deep = deep(stack);
        while (deep > 0) {
            int max = max(stack, deep);
            int times = times(stack, max, deep);
            down(stack, max, deep, times);
            deep -= times;
        }
    }

    public static void down(Stack<Integer> stack, int max, int deep, int times) {
        if (deep == 0) {
            for (int i = 0; i < times; i++) {
                stack.push(max);
            }
        } else {
            int num = stack.pop();
            down(stack, max, deep - 1, times);
            if (max != num) {
                stack.push(num);
            }
        }
    }

    // 递归求解栈的深度
    public static int deep(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int num = stack.pop();
        int deep = deep(stack) + 1;
        stack.push(num);
        return deep;
    }

    // 1～deep层里面
    // 最大值是多少
    public static int max(Stack<Integer> stack, int deep) {
        if (deep == 0) {
            return Integer.MIN_VALUE;
        }
        int num = stack.pop();
        int max = Math.max(num, max(stack, deep - 1));
        stack.push(num);
        return max;
    }

    // 0~deep层里，最大值max，出现的次数
    public static int times(Stack<Integer> stack, int max, int deep) {
        if (deep == 0) {
            return 0;
        }
        int num = stack.pop();
        int ans = times(stack, max, deep - 1) + (max == num ? 1 : 0);
        stack.push(num);
        return ans;
    }

    // for test
    public static Stack<Integer> generateRandomStack(int n, int v) {
        Stack<Integer> ans = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            ans.add((int) (Math.random() * v));
        }
        return ans;
    }

    public static boolean isSorted(Stack<Integer> stack) {
        int step = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            if (step > stack.peek()) {
                return false;
            }
            step = stack.pop();
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 20;
        int V = 20;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N);
            Stack<Integer> stack = generateRandomStack(n, V);
            sortStack(stack);
            if (!isSorted(stack)) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
