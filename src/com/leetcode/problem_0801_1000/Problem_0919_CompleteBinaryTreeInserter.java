package com.leetcode.problem_0801_1000;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ycb
 * @date 2022/7/25-09:57
 */
public class Problem_0919_CompleteBinaryTreeInserter {

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

    class CBTInserter {

        private final Queue<TreeNode> cands;
        private final TreeNode root;

        public CBTInserter(TreeNode root) {
            cands = new ArrayDeque<>();
            this.root = root;

            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (!(cur.left != null && cur.right != null)) {
                    cands.add(cur);
                }
            }
        }

        public int insert(int val) {
            TreeNode child = new TreeNode(val);
            TreeNode node = cands.peek();
            int res = node.val;
            if (node.left == null) {
                node.left = child;
            } else {
                node.right = child;
                cands.poll();
            }
            cands.offer(child);
            return res;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
