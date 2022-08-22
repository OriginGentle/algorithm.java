package com.leetcode.problem_0601_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/8/22-10:18
 * @desc
 */
public class Problem_0655_PrintBinaryTree {

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

    public int h, m, n;
    public List<List<String>> ans;

    public List<List<String>> printTree(TreeNode root) {
        ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        process(root, 0);
        int m = h + 1, n = (1 << (h + 1)) - 1;

        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                ans.get(i).add("");
            }
        }

        dfs(root, 0, (n - 1) / 2);
        return ans;
    }

    private void dfs(TreeNode node, int row, int col) {
        if (node == null)
            return;
        ans.get(row).set(col, Integer.toString(node.val));
        dfs(node.left, row + 1, col - (1 << (h - row - 1)));
        dfs(node.right, row + 1, col + (1 << (h - row - 1)));
    }

    private void process(TreeNode node, int depth) {
        if (node == null)
            return;
        h = Math.max(h, depth);
        process(node.left, depth + 1);
        process(node.right, depth + 1);
    }
}
