package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/8/30-08:42
 */
public class Problem_0998_MaximumBinaryTreeII {

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

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val)
            return new TreeNode(val, root, null);

        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
