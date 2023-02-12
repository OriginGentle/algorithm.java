package com.leetcode.questions.problem_0401_0600;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/6/22-19:36
 */
public class Problem_0513_FindBottomLeftTreeValue {

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

    public int max;
    public int ans;

    public int findBottomLeftValue1(TreeNode root) {
        if (root == null) {
            return -1;
        }
        dfs(root, 1);
        return ans;
    }

    public void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (depth > max) {
            max = depth;
            ans = node.val;
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    /*
    ====================================================================================================================
     */

    public static int findBottomLeftValue2(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int ans = -1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            ans = deque.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.pollFirst();
                if (cur.left != null) deque.addLast(cur.left);
                if (cur.right != null) deque.addLast(cur.right);
            }
        }
        return ans;
    }

}
