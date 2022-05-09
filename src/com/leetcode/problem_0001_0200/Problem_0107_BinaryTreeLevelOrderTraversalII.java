package com.leetcode.problem_0001_0200;

import java.util.*;

/**
 * @author ycb
 * @since 2022/3/9-10:23
 */
public class Problem_0107_BinaryTreeLevelOrderTraversalII {

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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int level = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                curAns.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            map.put(level++, curAns);
        }
        for (int i = level - 1; i >= 0; i--) {
            ans.add(map.get(i));
        }
        return ans;
    }
}
