package com.leetcode.questions.problem_0601_0800;

import java.util.ArrayDeque;

/**
 * @author ycb
 * @since 2021/10/18-15:56
 */
public class Problem_0662_MaximumWidthOfBinaryTree {

    public static class TreeNode {
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

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayDeque<NodeRecord> queue = new ArrayDeque<>();
        queue.add(new NodeRecord(root, 0, 0));
        int ans = 0, level = 0, pos = 0;
        while (!queue.isEmpty()) {
            NodeRecord cur = queue.poll();
            if (cur.node != null) {
                queue.add(new NodeRecord(cur.node.left, cur.depth + 1, cur.position << 1));
                queue.add(new NodeRecord(cur.node.right, cur.depth + 1, (cur.position << 1) + 1));

                if (level != cur.depth) {
                    level = cur.depth;
                    pos = cur.position;
                }
                ans = Math.max(ans, cur.position - pos + 1);
            }
        }
        return ans;
    }

    public static class NodeRecord {
        public TreeNode node;
        public int depth;
        public int position;

        public NodeRecord(TreeNode t, int d, int p) {
            node = t;
            depth = d;
            position = p;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        int ans = widthOfBinaryTree(root);
        System.out.println(ans);
    }
}
