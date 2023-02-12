package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/6/20-21:06
 */
public class Problem_0715_RangeModule {

    class RangeModule {

        class Node {
            int ls, rs, sum, add;
        }

        int N = (int) 1e9 + 10, M = 500010, cnt = 1;
        Node[] tree = new Node[M];

        void update(int u, int lc, int rc, int l, int r, int v) {
            int len = rc - lc + 1;
            if (l <= lc && rc <= r) {
                tree[u].sum = v == 1 ? len : 0;
                tree[u].add = v;
                return;
            }
            pushDown(u, len);
            int mid = lc + rc >> 1;
            if (l <= mid) update(tree[u].ls, lc, mid, l, r, v);
            if (r > mid) update(tree[u].rs, mid + 1, rc, l, r, v);
            pushUp(u);
        }

        int query(int u, int lc, int rc, int l, int r) {
            if (l <= lc && rc <= r) return tree[u].sum;
            pushDown(u, rc - lc + 1);
            int mid = lc + rc >> 1, ans = 0;
            if (l <= mid) ans = query(tree[u].ls, lc, mid, l, r);
            if (r > mid) ans += query(tree[u].rs, mid + 1, rc, l, r);
            return ans;
        }

        void pushDown(int u, int len) {
            if (tree[u] == null) tree[u] = new Node();
            if (tree[u].ls == 0) {
                tree[u].ls = ++cnt;
                tree[tree[u].ls] = new Node();
            }
            if (tree[u].rs == 0) {
                tree[u].rs = ++cnt;
                tree[tree[u].rs] = new Node();
            }
            if (tree[u].add == 0) return;
            if (tree[u].add == -1) {
                tree[tree[u].ls].sum = tree[tree[u].rs].sum = 0;
            } else {
                tree[tree[u].ls].sum = len - len / 2;
                tree[tree[u].rs].sum = len / 2;
            }
            tree[tree[u].ls].add = tree[tree[u].rs].add = tree[u].add;
            tree[u].add = 0;
        }

        void pushUp(int u) {
            tree[u].sum = tree[tree[u].ls].sum + tree[tree[u].rs].sum;
        }

        public void addRange(int left, int right) {
            update(1, 1, N - 1, left, right - 1, 1);
        }

        public boolean queryRange(int left, int right) {
            return query(1, 1, N - 1, left, right - 1) == right - left;
        }

        public void removeRange(int left, int right) {
            update(1, 1, N - 1, left, right - 1, -1);
        }
    }
}
