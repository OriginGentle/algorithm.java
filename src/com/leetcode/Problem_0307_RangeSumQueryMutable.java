package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/4
 */
public class Problem_0307_RangeSumQueryMutable {

//    class NumArray {
//
//        private SegmentTree segmentTree;
//        int size;
//
//        public NumArray(int[] nums) {
//            size = nums.length;
//            segmentTree = new SegmentTree(nums);
//            segmentTree.build(1, size, 1);
//        }
//
//        public void update(int index, int val) {
//            segmentTree.update(index, index, val, 1, size, 1);
//        }
//
//        public int sumRange(int left, int right) {
//            long ans = segmentTree.query(left, right, 1, size, 1);
//            return (int) ans;
//        }
//
//        public class SegmentTree {
//            private int[] arr;
//            private int[] sum;
//            private int[] lazy;
//            private int[] change;
//            private boolean[] update;
//
//            public SegmentTree(int[] origin) {
//                int n = origin.length + 1;
//                arr = new int[n + 1];
//                for (int i = 1; i < n; i++) {
//                    arr[i] = origin[i - 1];
//                }
//                sum = new int[n << 2];
//                lazy = new int[n << 2];
//                change = new int[n << 2];
//                update = new boolean[n << 2];
//            }
//
//            private void pushUp(int curPos) {
//                sum[curPos] = sum[curPos << 1] + sum[curPos << 1 | 1];
//            }
//
//            private void pushDown(int curPos, int lCount, int rCount) {
//                if (update[curPos]) {
//                    update[curPos << 1] = true;
//                    update[curPos << 1 | 1] = true;
//                    change[curPos << 1] = change[curPos];
//                    change[curPos << 1 | 1] = change[curPos];
//                    lazy[curPos << 1] = 0;
//                    lazy[curPos << 1 | 1] = 0;
//                    sum[curPos << 1] = change[curPos] * lCount;
//                    sum[curPos << 1 | 1] = change[curPos] * rCount;
//                }
//                if (lazy[curPos] != 0) {
//                    lazy[curPos << 1] += lazy[curPos];
//                    sum[curPos << 1] += lazy[curPos] * lCount;
//                    lazy[curPos << 1 | 1] += lazy[curPos];
//                    sum[curPos << 1 | 1] += lazy[curPos] * rCount;
//                    lazy[curPos] = 0;
//                }
//            }
//
//            public void build(int l, int r, int curPos) {
//                if (l == r) {
//                    sum[curPos] = arr[l];
//                    return;
//                }
//                int mid = (l + r) >> 1;
//                build(l, mid, curPos << 1);
//                build(mid + 1, r, curPos << 1 | 1);
//                pushUp(curPos);
//            }
//
//            public void update(int L, int R, int C, int l, int r, int curPos) {
//                if (L <= l && r <= R) {
//                    update[curPos] = true;
//                    change[curPos] = C;
//                    sum[curPos] = C * (r - l + 1);
//                    lazy[curPos] = 0;
//                    return;
//                }
//                int mid = (l + r) >> 1;
//                pushDown(curPos, mid - l + 1, r - mid);
//                if (L <= mid) {
//                    update(L, R, C, l, mid, curPos << 1);
//                }
//                if (R > mid) {
//                    update(L, R, C, mid + 1, r, curPos << 1 | 1);
//                }
//                pushUp(curPos);
//            }
//
//            public long query(int L, int R, int l, int r, int curPos) {
//                if (L <= l && r <= R) {
//                    return sum[curPos];
//                }
//                int mid = (l + r) >> 1;
//                pushDown(curPos, mid - l + 1, r - mid);
//                long ans = 0;
//                if (L <= mid) {
//                    ans += query(L, R, l, mid, curPos << 1);
//                }
//                if (R > mid) {
//                    ans += query(L, R, mid + 1, r, curPos << 1 | 1);
//                }
//                return ans;
//            }
//        }
//    }

    class NumArray {
        private int[] segmentTree;
        private int n;

        public NumArray(int[] nums) {
            n = nums.length;
            segmentTree = new int[nums.length * 4];
            build(0, 0, n - 1, nums);
        }

        public void update(int index, int val) {
            change(index, val, 0, 0, n - 1);
        }

        public int sumRange(int left, int right) {
            return range(left, right, 0, 0, n - 1);
        }

        private void build(int node, int s, int e, int[] nums) {
            if (s == e) {
                segmentTree[node] = nums[s];
                return;
            }
            int m = s + (e - s) / 2;
            build(node * 2 + 1, s, m, nums);
            build(node * 2 + 2, m + 1, e, nums);
            segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
        }

        private void change(int index, int val, int node, int s, int e) {
            if (s == e) {
                segmentTree[node] = val;
                return;
            }
            int m = s + (e - s) / 2;
            if (index <= m) {
                change(index, val, node * 2 + 1, s, m);
            } else {
                change(index, val, node * 2 + 2, m + 1, e);
            }
            segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
        }

        private int range(int left, int right, int node, int s, int e) {
            if (left == s && right == e) {
                return segmentTree[node];
            }
            int m = s + (e - s) / 2;
            if (right <= m) {
                return range(left, right, node * 2 + 1, s, m);
            } else if (left > m) {
                return range(left, right, node * 2 + 2, m + 1, e);
            } else {
                return range(left, m, node * 2 + 1, s, m) + range(m + 1, right, node * 2 + 2, m + 1, e);
            }
        }
    }

}
