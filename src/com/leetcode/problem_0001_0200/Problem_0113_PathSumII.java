package com.leetcode.problem_0001_0200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/3/25
 */
public class Problem_0113_PathSumII {

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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<Integer> path = new LinkedList<>();
        process(root, path, ans, targetSum);
        return ans;
    }

    private void process(TreeNode node, LinkedList<Integer> path, List<List<Integer>> ans, int target) {
        if (node == null) {
            return;
        }
        path.addLast(node.val);
        target -= node.val;
        if (node.left == null && node.right == null && target == 0) {
            ans.add(new ArrayList<>(path));
        }
        process(node.left, path, ans, target);
        process(node.right, path, ans, target);
        path.pollLast();
    }
}
