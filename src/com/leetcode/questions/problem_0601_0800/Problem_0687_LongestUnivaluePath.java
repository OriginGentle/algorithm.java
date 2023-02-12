package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/9/2-16:31
 */
public class Problem_0687_LongestUnivaluePath {

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

    public int res;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;

        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int lc = 0, rc = 0;
        if (node.left != null && node.left.val == node.val)
            lc = left + 1;

        if (node.right != null && node.right.val == node.val)
            rc = right + 1;

        res = Math.max(res, lc + rc);
        return Math.max(lc, rc);
    }
}
