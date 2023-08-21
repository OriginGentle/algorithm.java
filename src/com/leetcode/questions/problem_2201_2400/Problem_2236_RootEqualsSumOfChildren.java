package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/8/20-23:39
 */
public class Problem_2236_RootEqualsSumOfChildren {

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

    public static boolean checkTree(TreeNode root) {

        return root.val == root.left.val + root.right.val;
    }
}
