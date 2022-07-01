package com.weekly.code_2022_06_4_week;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author ycb
 * @date 2022/7/1-13:35
 * @desc 来自微软
 * 请设计一种叫做“栈的管理器”的结构，实现如下6个功能
 * 1) void createNewStack() : 可以在该结构中生成一个栈结构，编号从0开始
 * 2) void push(int num, int stackIndex) : 将编号为stackIndex的栈里，压入num
 * 3) int pop(int stackIndex) : 从编号为stackIndex的栈里，弹出栈顶返回
 * 4) int peek(int stackIndex) ：从编号为stackIndex的栈里，返回栈顶但是不弹出
 * 5) boolean isEmpty(int statckIndex)：返回编号为stackIndex的栈是否为空
 * 6) int stackSize() : 返回一共生成了多少个栈
 * 要求：不管用户调用多少次上面的方法，只使用有限几个动态数组(常数个)，完成代码实现
 */
public class Code02_StackNotSplit {

    public static class Stacks1 {

        ArrayList<Stack<Integer>> stacks;

        public Stacks1() {
            stacks = new ArrayList<>();
        }

        public void createNewStack() {
            stacks.add(new Stack<>());
        }

        public void push(int num, int stackIndex) {
            stacks.get(stackIndex).push(num);
        }

        public int pop(int stackIndex) {
            return stacks.get(stackIndex).pop();
        }

        public int peek(int stackIndex) {
            return stacks.get(stackIndex).peek();
        }

        public boolean isEmpty(int stackIndex) {
            return stacks.get(stackIndex).isEmpty();
        }

        public int stackSize() {
            return stacks.size();
        }
    }

    /*
    ====================================================================================================================
     */

    public static class StackManager {
        // 栈数组
        public ArrayList<Integer> heads;
        // 值数组
        public ArrayList<Integer> values;
        // 上数组
        public ArrayList<Integer> lasts;
        // 垃圾数组
        public ArrayList<Integer> frees;
        // 值数组用到了哪里
        public int occupySize;
        public int freeSize;

        public StackManager() {
            heads = new ArrayList<>();
            values = new ArrayList<>();
            lasts = new ArrayList<>();
            frees = new ArrayList<>();
            occupySize = 0;
            freeSize = 0;
        }

        public void createNewStack() {
            heads.add(-1);
        }

        public int stackSize() {
            return heads.size();
        }

        public boolean isEmpty(int stackIndex) {
            return heads.get(stackIndex) == -1;
        }

        public int peek(int stackIndex) {
            return values.get(heads.get(stackIndex));
        }

        public void push(int num, int stackIndex) {
            // 老头部
            // 新头部往前跳，要能找到老头部
            int oldHeadIndex = heads.get(stackIndex);
            if (freeSize == 0) { // 垃圾区没有空闲
                heads.set(stackIndex, occupySize++);
                values.add(num);
                lasts.add(oldHeadIndex);
            } else {
                // 新的数 -->  freeIndex
                int freeIndex = frees.get(--freeSize);
                heads.set(stackIndex, freeIndex);
                values.set(freeIndex, num);
                lasts.set(freeIndex, oldHeadIndex);
            }
        }

        public int pop(int stackIndex) {
            // 当前的头部位置
            int headIndex = heads.get(stackIndex);
            int ans = values.get(headIndex);
            int newHeadIndex = lasts.get(headIndex);
            heads.set(stackIndex, newHeadIndex);
            // 垃圾区要接收此时的头部
            if (freeSize < frees.size()) {
                frees.set(freeSize++, headIndex);
            } else {
                frees.add(headIndex);
                freeSize++;
            }
            return ans;
        }
    }

    // for test
    public static void main(String[] args) {
        int V = 10000;
        int testTime = 20000;
        System.out.println("测试开始");
        Stacks1 stack1 = new Stacks1();
        StackManager stack2 = new StackManager();
        for (int i = 0; i < testTime; i++) {
            double decide = Math.random();
            if (decide < 0.25) {
                stack1.createNewStack();
                stack2.createNewStack();
            } else {
                int stackSize1 = stack1.stackSize();
                int stackSize2 = stack2.stackSize();
                if (stackSize1 != stackSize2) {
                    System.out.println("栈的数量不一致！");
                    break;
                }
                if (stackSize1 > 0) {
                    int stackIndex = (int) (Math.random() * stackSize1);
                    if (decide < 0.5) {
                        int num = (int) (Math.random() * V);
                        stack1.push(num, stackIndex);
                        stack2.push(num, stackIndex);
                    } else if (decide < 0.75) {
                        if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
                            System.out.println(stackIndex + "号栈的是否为空不一致！");
                            break;
                        }
                        if (!stack1.isEmpty(stackIndex)) {
                            if (stack1.pop(stackIndex) != stack2.pop(stackIndex)) {
                                System.out.println(stackIndex + "号栈的弹出数据不一致！");
                                break;
                            }
                        }
                    } else {
                        if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
                            System.out.println(stackIndex + "号栈的是否为空不一致！");
                            break;
                        }
                        if (!stack1.isEmpty(stackIndex)) {
                            if (stack1.peek(stackIndex) != stack2.peek(stackIndex)) {
                                System.out.println(stackIndex + "号栈的栈顶数据不一致！");
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
