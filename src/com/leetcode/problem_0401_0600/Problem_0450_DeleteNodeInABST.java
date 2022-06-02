package com.leetcode.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/6/2-15:48
 */
public class Problem_0450_DeleteNodeInABST {

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

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {

            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            TreeNode tmp = root.left;
            while (tmp.right != null) tmp = tmp.right;
            tmp.right = root.right;
            return root.left;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
}
