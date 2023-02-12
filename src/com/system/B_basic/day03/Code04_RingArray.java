package com.system.B_basic.day03;

/**
 * @author ycb
 * @since 2021/11/23-15:16
 */
public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushI;// end
        private int pollI;// begin
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushI = 0;
            pollI = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushI] = value;
            pushI = nextIndex(pushI);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[pollI];
            pollI = nextIndex(pollI);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }
}
