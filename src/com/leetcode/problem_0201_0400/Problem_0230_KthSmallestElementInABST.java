package com.leetcode.problem_0201_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/9/6-15:40
 */
public class Problem_0230_KthSmallestElementInABST {

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

    public static int kthSmallest1(TreeNode root, int k) {
        List<Integer> cnt = new ArrayList<>();
        dfs(root, cnt);
        return cnt.get(k - 1);
    }

    private static void dfs(TreeNode node, List<Integer> cnt) {
        if (node == null)
            return;
        dfs(node.left, cnt);
        cnt.add(node.val);
        dfs(node.right, cnt);
    }

    /*
    ====================================================================================================================
     */

    public static int kth, target;

    public static int kthSmallest2(TreeNode root, int k) {
        kth = k;
        target = -1;
        process(root);
        return target;
    }

    private static void process(TreeNode node) {
        if (node == null)
            return;
        process(node.left);
        if (--kth == 0) {
            target = node.val;
            return;
        }
        process(node.right);
    }
}
