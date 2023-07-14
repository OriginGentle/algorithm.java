package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2023/7/14-08:39
 */
public class Problem_0979_DistributeCoinsInBinaryTree {

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

    public static int move;

    public static int distributeCoins(TreeNode root) {
        move = 0;
        dfs(root);
        return move;
    }

    private static int dfs(TreeNode root) {
        int moveLeft = 0, moveRight = 0;
        if (root == null) {
            return 0;
        }

        if (root.left != null) {
            moveLeft = dfs(root.left);
        }

        if (root.right != null) {
            moveRight = dfs(root.right);
        }

        move += Math.abs(moveLeft) + Math.abs(moveRight);

        return moveLeft + moveRight + root.val - 1;
    }
}
