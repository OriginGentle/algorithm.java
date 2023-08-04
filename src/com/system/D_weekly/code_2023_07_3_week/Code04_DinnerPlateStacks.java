package com.system.D_weekly.code_2023_07_3_week;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/7/25-22:46
 * @desc 我们把无限数量的栈排成一行，按从左到右的次序从 0 开始编号
 * 每个栈的的最大容量 capacity 都相同。实现一个叫「餐盘」的类 DinnerPlates
 * DinnerPlates(int capacity) - 给出栈的最大容量 capacity
 * void push(int val) 将给出的正整数 val 推入 从左往右第一个 没有满的栈
 * int pop() 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除
 * 如果所有的栈都是空的，请返回 -1。
 * int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除
 * 如果编号 index 的栈是空的，请返回 -1。
 * 测试链接 : https://leetcode.cn/problems/dinner-plate-stacks/
 */
public class Code04_DinnerPlateStacks {

    static class DinnerPlates {

        private final int N = 100001;

        private final int capacity;

        // 所有栈结构
        private final List<List<Integer>> stacks = new ArrayList<>();

        // 每个栈有多少个数字
        private final int[] cnt = new int[N + 1];

        private final PriorityQueue<Integer> heap = new PriorityQueue<>();

        private int mostRightStack;

        public DinnerPlates(int cap) {
            capacity = cap;
            mostRightStack = 0;
        }

        public int pop() {
            while (cnt[mostRightStack] == 0 && mostRightStack > 0) {
                mostRightStack--;
            }

            if (cnt[mostRightStack] == 0) {
                return -1;
            }

            return stacks.get(mostRightStack).remove(--cnt[mostRightStack]);
        }


        public int popAtStack(int index) {
            if (cnt[index] == 0) {
                return -1;
            }

            int ans = stacks.get(index).remove(cnt[index] - 1);

            if (cnt[index] == capacity) {
                heap.add(index);
            }

            cnt[index]--;
            return ans;
        }

        public void push(int val) {
            if (heap.isEmpty()) {
                // 没有空洞
                // 0.....mostRightStack
                if (cnt[mostRightStack] == capacity && mostRightStack < N) {
                    mostRightStack++;
                }
                if (stacks.size() == mostRightStack) {
                    stacks.add(new ArrayList<>());
                }
                stacks.get(mostRightStack).add(val);
                cnt[mostRightStack]++;
            } else {
                // 有空洞
                // rightStack往左来到该去的位置，往左缩
                while (cnt[mostRightStack] == 0 && mostRightStack > 0) {
                    mostRightStack--;
                }
                if (heap.peek() >= mostRightStack) {
                    // 所有空洞，没用了
                    while (!heap.isEmpty()) {
                        heap.poll();
                    }
                    // 0.....rightStack
                    if (cnt[mostRightStack] == capacity) {
                        mostRightStack++;
                    }
                    if (stacks.size() == mostRightStack) {
                        stacks.add(new ArrayList<>());
                    }
                    stacks.get(mostRightStack).add(val);
                    cnt[mostRightStack]++;
                } else {
                    // 不是所有空洞都失效
                    int cur = heap.peek();
                    cnt[cur]++;
                    stacks.get(cur).add(val);
                    if (cnt[cur] == capacity) {
                        heap.poll();
                    }
                }
            }
        }
    }
}
