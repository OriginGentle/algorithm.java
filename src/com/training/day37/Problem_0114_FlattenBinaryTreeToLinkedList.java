package com.training.day37;

/**
 * @author ycb
 * @since 2021/10/25-8:52
 */
public class Problem_0114_FlattenBinaryTreeToLinkedList {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 一般解
    public static TreeNode convert(TreeNode head) {
        if (head == null) {
            return null;
        }
        return process(head).head;
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        if (leftInfo != null) {
            leftInfo.tail.right = x;
            x.left = leftInfo.tail;
        }
        if (rightInfo != null) {
            rightInfo.head.left = x;
            x.right = rightInfo.head;
        }
        TreeNode head = leftInfo != null ? leftInfo.head : x;
        TreeNode tail = rightInfo != null ? rightInfo.tail : x;
        return new Info(head, tail);
    }

    public static class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

    /*
    ====================================================================================================================
     */

    // 利用Morris遍历
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode pre = null;
        TreeNode cur = root;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    if (pre != null) {
                        pre.left = cur;
                    }
                    pre = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                if (pre != null) {
                    pre.left = cur;
                }
                pre = cur;
            }
            cur = cur.right;
        }
        cur = root;
        TreeNode next = null;
        while (cur != null) {
            next = cur.left;
            cur.left = null;
            cur.right = next;
            cur = next;
        }
    }
}
