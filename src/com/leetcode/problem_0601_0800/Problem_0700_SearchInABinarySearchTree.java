package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @since 2021/11/26-8:42
 */
public class Problem_0700_SearchInABinarySearchTree {

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

    public static TreeNode searchBST(TreeNode root, int val) {
        return process(root, val);
    }

    public static TreeNode process(TreeNode x, int target) {
        if (x == null || x.val == target) {
            return x;
        }
        return x.val > target ? process(x.left, target) : process(x.right, target);
    }
}
