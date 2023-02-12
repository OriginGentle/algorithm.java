package com.system.C_training.day34;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/10/22-9:23
 */
public class Problem_0295_FindMedianFromDataStream {

    class MedianFinder {
        PriorityQueue<Integer> maxQ;
        PriorityQueue<Integer> minQ;

        public MedianFinder() {
            maxQ = new PriorityQueue<>((a, b) -> b - a);
            minQ = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (maxQ.isEmpty() || maxQ.peek() >= num) {
                maxQ.add(num);
            } else {
                minQ.add(num);
            }
            balances();
        }

        public double findMedian() {
            if (maxQ.size() == minQ.size()) {
                return (double) (maxQ.peek() + minQ.peek()) / 2;
            } else {
                return maxQ.size() > minQ.size() ? maxQ.peek() : minQ.peek();
            }
        }

        public void balances() {
            if (Math.abs(maxQ.size() - minQ.size()) == 2) {
                if (maxQ.size() > minQ.size()) {
                    minQ.add(maxQ.poll());
                } else {
                    maxQ.add(minQ.poll());
                }
            }
        }
    }
}
