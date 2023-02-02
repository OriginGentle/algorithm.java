package com.leetcode.problem_interview;

/**
 * @author ycb
 * @date 2022/5/16-08:03
 */
public class Problem_04_06_SuccessorLCCI {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        TreeNode ans = inorderSuccessor(root.left, p);
        return ans == null ? root : ans;
    }

}
