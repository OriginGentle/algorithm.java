package com.training.day30;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/16-21:45
 */
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 0;
        boolean isHead = true;
        while (!queue.isEmpty()) {
            size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = isHead ? queue.pollFirst() : queue.pollLast();
                curLevel.add(cur.val);
                if (isHead) {
                    if (cur.left != null) {
                        queue.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        queue.addLast(cur.right);
                    }
                } else {
                    if (cur.right != null) {
                        queue.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        queue.addFirst(cur.left);
                    }
                }
            }
            ans.add(curLevel);
            isHead = !isHead;
        }
        return ans;
    }
}
