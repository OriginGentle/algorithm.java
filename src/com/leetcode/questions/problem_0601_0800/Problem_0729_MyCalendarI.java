package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/7/5-08:22
 */
public class Problem_0729_MyCalendarI {

    class MyCalendar {
        public DynamicSegmentTree dst;
        public int size = (int) 1e9;

        public MyCalendar() {
            dst = new DynamicSegmentTree(size);
        }

        public boolean book(int start, int end) {
            // 先查询该区间是否为 0
            if (dst.query(start, end - 1) != 0)
                return false;
            // 更新该区间
            dst.update(start, end - 1, 1);
            return true;
        }
    }

    class Node {
        // 左右孩子节点
        Node left, right;
        // 当前节点值，以及懒惰标记的值
        int val, add;
    }

    class DynamicSegmentTree {

        private int N;

        public Node root;

        public DynamicSegmentTree(int size) {
            N = size;
            root = new Node();
        }

        public void update(int s, int e, int v) {
            update(root, 0, N, s, e, v);
        }

        private void update(Node node, int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                node.val += val;
                node.add += val;
                return;
            }
            pushDown(node);
            int mid = (start + end) >> 1;
            if (l <= mid) update(node.left, start, mid, l, r, val);
            if (r > mid) update(node.right, mid + 1, end, l, r, val);
            pushUp(node);
        }

        public int query(int s, int e) {
            return query(root, 0, N, s, e);
        }

        public int query(Node node, int start, int end, int l, int r) {
            if (l <= start && end <= r) return node.val;
            pushDown(node);
            int mid = (start + end) >> 1, ans = 0;
            if (l <= mid) ans = query(node.left, start, mid, l, r);
            if (r > mid) ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
            return ans;
        }

        private void pushUp(Node node) {
            // 每个节点存的是当前区间的最大值
            node.val = Math.max(node.left.val, node.right.val);
        }

        private void pushDown(Node node) {
            if (node.left == null) node.left = new Node();
            if (node.right == null) node.right = new Node();
            if (node.add == 0) return;
            node.left.val += node.add;
            node.right.val += node.add;
            node.left.add += node.add;
            node.right.add += node.add;
            node.add = 0;
        }
    }
}
