package com.peter.day03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ycb
 * @Date 2021/3/18-14:29
 * @Description 用队列结构实现栈结构
 */
public class TwoQueueImplementStack {

    public static class towQueuesStack<T> {
        private Queue<T> queue;
        private Queue<T> help;

        public towQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void offer(T value) {
            queue.offer(value);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = help.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = help.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
