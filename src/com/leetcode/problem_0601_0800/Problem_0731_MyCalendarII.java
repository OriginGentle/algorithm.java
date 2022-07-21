package com.leetcode.problem_0601_0800;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ycb
 * @date 2022/7/21-15:11
 */
public class Problem_0731_MyCalendarII {

    class MyCalendarTwo {

        private DynamicSegmentTree dst;

        private int MAX = (int) 1e9;

        public MyCalendarTwo() {
            dst = new DynamicSegmentTree(MAX);
        }

        public boolean book(int start, int end) {
            if (dst.query(start, end) >= 2)
                return false;
            dst.add(start, end, 1);
            return true;
        }
    }

    class Node {
        public Node left, right;
        public int max, add;
    }

    class DynamicSegmentTree {
        public int N;
        public Node root;

        public DynamicSegmentTree(int size) {
            N = size;
            root = new Node();
        }

        public int query(int start, int end) {
            return query(root, 0, N, start, end - 1);
        }

        private int query(Node node, int l, int r, int start, int end) {
            if (start <= l && r <= end) {
                return node.max;
            }
            pushDown(node);
            int m = (l + r) >> 1;
            int ans = 0;
            if (start <= m)
                ans = query(node.left, l, m, start, end);
            if (end > m) {
                ans = Math.max(ans, query(node.right, m + 1, r, start, end));
            }
            return ans;
        }

        public void add(int start, int end, int val) {
            add(root, 0, N, start, end - 1, val);
        }

        private void add(Node node, int l, int r, int start, int end, int val) {
            if (start <= l && r <= end) {
                node.add += val;
                node.max += val;
                return;
            }
            pushDown(node);
            int m = (l + r) >> 1;
            if (start <= m)
                add(node.left, l, m, start, end, val);
            if (end > m)
                add(node.right, m + 1, r, start, end, val);
            pushUp(node);
        }

        private void pushDown(Node node) {
            if (node.left == null) {
                node.left = new Node();
            }
            if (node.right == null) {
                node.right = new Node();
            }
            node.left.add += node.add;
            node.right.add += node.add;
            node.left.max += node.add;
            node.right.max += node.add;
            node.add = 0;
        }

        private void pushUp(Node node) {
            node.max = Math.max(node.left.max, node.right.max);
        }
    }
}
