package com.system.III_training.day35;

/**
 * @author ycb
 * @since 2021/10/23-13:26
 */
public class Problem_0687_LongestUnivaluePath {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).max - 1;
    }

    public static class Info {
        // 路径必须从x出发且只能往下走的情况下，路径的最大距离
        public int len;
        // 路径不要求必须从x出发的情况下，整棵树的合法路径最大距离
        public int max;

        public Info(int l, int m) {
            len = l;
            max = m;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        TreeNode l = x.left;
        TreeNode r = x.right;
        // 左树上，不要求从左孩子出发，最大路径
        // 左树上，必须从左孩子出发，往下的最大路径
        Info left = process(l);
        // 右树上，不要求从右孩子出发，最大路径
        // 右树上，必须从右孩子出发，往下的最大路径
        Info right = process(r);
        int len = 1;
        if (l != null && l.val == x.val) {
            len = left.len + 1;
        }
        if (r != null && r.val == x.val) {
            len = Math.max(len, right.len + 1);
        }
        // 不要求从x出发，最大路径
        int max = Math.max(Math.max(left.max, right.max), len);
        if (l != null && r != null && l.val == x.val && r.val == x.val) {
            max = Math.max(max, left.len + right.len + 1);
        }
        return new Info(len, max);
    }
}
