package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayDeque;

/**
 * @author ycb
 * @date 2022/5/24-08:17
 */
public class Problem_0965_UnivaluedBinaryTree {

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

    public static boolean isUnivalTree1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean ans = true;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int sign = root.val;
        retry:
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.val != sign) {
                    ans = false;
                    break retry;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public int cnt = -1;

    public boolean isUnivalTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (cnt == -1) {
            cnt = root.val;
        }
        if (cnt != root.val) {
            return false;
        }
        return isUnivalTree2(root.left) && isUnivalTree2(root.right);
    }
}
