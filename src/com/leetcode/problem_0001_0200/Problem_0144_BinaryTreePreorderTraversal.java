package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/4/9
 */
public class Problem_0144_BinaryTreePreorderTraversal {

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

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        process(root, ans);
        return ans;
    }

    private void process(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        ans.add(node.val);
        process(node.left, ans);
        process(node.right, ans);
    }
}
