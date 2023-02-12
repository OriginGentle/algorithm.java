package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/3/19-15:59
 */
public class Problem_0606_ConstructStringFromBinaryTree {

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

    public String tree2str1(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        process(root, sb);
        return sb.toString();
    }

    public static void process(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            return;
        }
        sb.append("(");
        process(node.left, sb);
        sb.append(")");
        if (node.right != null) {
            sb.append("(");
            process(node.right, sb);
            sb.append(")");
        }
    }
}
