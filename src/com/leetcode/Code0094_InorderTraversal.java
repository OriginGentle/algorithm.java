package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/7/1-8:20
 * @Description
 */
public class Code0094_InorderTraversal {

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

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode cur = root;
        List<Integer> ans = new ArrayList<>();
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight  cur左树上最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }

}
