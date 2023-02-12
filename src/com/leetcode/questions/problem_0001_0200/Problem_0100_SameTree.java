package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/15-8:21
 */
public class Problem_0100_SameTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
