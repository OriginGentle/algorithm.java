package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2022/5/30-19:26
 */
public class Problem_1022_SumOfRootToLeafBinaryNumbers {

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

    public static int sumRootToLeaf(TreeNode root) {

        return dfs(root, 0);
    }

    public static int dfs(TreeNode node, int cur) {
        if (node == null) {
            return 0;
        }
        cur = (cur << 1) | node.val;
        if (node.left == null && node.right == null) {
            return cur;
        }
        return dfs(node.left, cur) + dfs(node.right, cur);
    }
}
