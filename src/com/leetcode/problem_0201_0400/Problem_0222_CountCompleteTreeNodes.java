package com.leetcode.problem_0201_0400;

import com.weekly.code_2022_08_4_week.Code04_MaxPalindromeNumber;

/**
 * @author ycb
 * @date 2022/9/1-09:49
 */
public class Problem_0222_CountCompleteTreeNodes {

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

    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int left = countLevel(root.left);
        int right = countLevel(root.right);

        if (left == right)
            return countNodes(root.right) + (1 << left);
        return countNodes(root.left) + (1 << right);
    }

    private static int countLevel(TreeNode node) {
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }
        return level;
    }
}
