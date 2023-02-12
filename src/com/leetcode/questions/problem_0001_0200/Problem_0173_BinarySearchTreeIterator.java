package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/8/16-19:22
 */
public class Problem_0173_BinarySearchTreeIterator {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class BSTIterator {

        private int idx;
        private ArrayList<TreeNode> cmt;

        public BSTIterator(TreeNode root) {
            idx = 0;
            cmt = new ArrayList<>();
            in(root, cmt);
        }

        private void in(TreeNode cur, ArrayList<TreeNode> cmt) {
            if (cur == null) {
                return;
            }
            in(cur.left, cmt);
            cmt.add(cur);
            in(cur.right, cmt);
        }

        public int next() {
            return cmt.get(idx++).val;
        }

        public boolean hasNext() {
            return idx < cmt.size();
        }
    }
}
