package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/4/9
 */
public class Problem_0145_BinaryTreePostorderTraversal {

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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private void process(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        process(node.left, ans);
        process(node.right, ans);
        ans.add(node.val);
    }
}
