package com.system.D_weekly.code_2023_07_3_week;

/**
 * @author ycb
 * @date 2023/7/25-22:46
 * @desc 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先
 * 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class Code05_LowestCommonAncestorOfBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root.val != p.val && root.val != q.val) {
            if (Math.min(p.val, q.val) < root.val &&
                    Math.max(p.val, q.val) > root.val) {
                break;
            }

            root = root.val < Math.min(p.val, q.val) ? root.right : root.left;
        }

        return root;
    }
}
