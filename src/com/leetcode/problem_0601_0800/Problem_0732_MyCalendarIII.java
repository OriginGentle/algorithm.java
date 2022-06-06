package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/6/6-08:04
 */
public class Problem_0732_MyCalendarIII {

    class MyCalendarThree {

        private SegmentTree segmentTree;

        private final int MAX_SIZE = (int) 1e9;

        public MyCalendarThree() {
            segmentTree = new SegmentTree(MAX_SIZE);
        }

        public int book(int start, int end) {
            segmentTree.add(start, end, 1);
            return segmentTree.max(0, MAX_SIZE);
        }
    }

    /*
    ====================================================================================================================
     */

    public static class SegmentTree {
        public int[] max;
        public int[] lazy;
        public int size;

        // 0位置弃而不用
        // 从1位置开始
        public SegmentTree(int maxSize) {
            size = maxSize + 1;
            max = new int[size];
            lazy = new int[size];
        }

        public int max(int start, int end) {
            return max(1, size, 1, start, end);
        }

        private int max(int lc, int rc, int ct, int L, int R) {
            if (L <= lc && rc <= R) {
                return max[ct];
            }
            int mid = (lc + rc) >> 1;
            pushDown(ct);
            int ans = 0;
            if (L <= mid) {
                ans = max(lc, mid, ct << 1, L, R);
            }
            if (R > mid) {
                ans = Math.max(ans, max(mid + 1, rc, ct << 1 | 1, L, R));
            }
            return ans;
        }

        public void add(int start, int end, int val) {
            add(1, size, 1, start, end, val);
        }

        // L...R 接到任务，所有值都加1
        // lc,rc,ct内部属性
        private void add(int lc, int rc, int ct, int L, int R, int C) {
            if (L <= lc && rc <= R) {
                lazy[ct] += C;
                return;
            }
            int mid = (lc + rc) >> 1;
            pushDown(ct);
            if (L <= mid) {
                add(lc, mid, ct << 1, L, R, C);
            }
            if (R > mid) {
                add(mid + 1, rc, ct << 1 | 1, L, R, C);
            }
            pushUp(ct);
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                max[rt << 1] += lazy[rt];
                max[rt << 1 | 1] += lazy[rt];
                lazy[rt] = 0;
            }
        }
    }

    /*
    ====================================================================================================================
     */

    // 开点线段树
    public static class DynamicSegmentTree {
        public Node root;
        public int size;

        public DynamicSegmentTree(int max) {
            root = new Node();
            size = max;
        }

        public int max(int start, int end) {
            return max(root, 0, size, start, end);
        }

        private int max(Node node, int lc, int rc, int start, int end) {
            if (start <= lc && rc <= end) {
                return node.maxVal;
            }
            pushDown(node);
            int ans = 0;
            int mid = lc + rc >> 1;
            if (start <= mid) {
                ans = max(node.left, lc, mid, start, end);
            }
            if (end > mid) {
                ans = Math.max(ans, max(node.right, mid + 1, rc, start, end));
            }
            return ans;
        }

        public void add(int start, int end, int val) {
            add(root, 0, size, start, end, val);
        }

        private void add(Node node, int lc, int rc, int start, int end, int val) {
            if (start <= lc && rc <= end) {
                node.lazy += val;
                node.maxVal += val;
                return;
            }
            pushDown(node);
            int mid = lc + rc >> 1;
            if (start <= mid) {
                add(node.left, lc, mid, start, end, val);
            }
            if (end > mid) {
                add(node.right, mid + 1, rc, start, end, val);
            }
            pushUp(node);
        }

        public void pushUp(Node cur) {
            cur.maxVal = Math.max(cur.left.maxVal, cur.right.maxVal);
        }

        private void pushDown(Node cur) {
            if (cur.left == null) {
                cur.left = new Node();
            }
            if (cur.right == null) {
                cur.right = new Node();
            }
            if (cur.lazy == 0) {
                return;
            }
            cur.left.lazy += cur.lazy;
            cur.left.maxVal += cur.lazy;
            cur.right.lazy += cur.lazy;
            cur.right.maxVal += cur.lazy;
            cur.lazy = 0;
        }
    }

    public static class Node {
        public Node left;
        public Node right;
        public int lazy;
        public int maxVal;
    }

    public static void main(String[] args) {
        Node node = new Node();
        System.out.println(node.lazy + "_" + node.maxVal);
    }
}
