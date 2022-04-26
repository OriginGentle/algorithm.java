package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/26
 */
public class Problem_0129_SumRootToLeafNumbers {

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

    public static int sumNumbers(TreeNode root) {
        return process(root, 0);
    }

    public static int process(TreeNode x, int preSum) {
        if (x == null) {
            return 0;
        }
        int sum = preSum * 10 + x.val;
        if (x.left == null && x.right == null) {
            return sum;
        }
        return process(x.left, sum) + process(x.right, sum);
    }
}
