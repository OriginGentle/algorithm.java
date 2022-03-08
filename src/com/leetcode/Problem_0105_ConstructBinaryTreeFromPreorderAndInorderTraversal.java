package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/3/8-13:19
 */
public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static class TreeNode {
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

    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode process(int[] p, int ps, int pe, int[] i, int is, int ie) {
        if (ps > pe) {
            return null;
        }
        TreeNode head = new TreeNode(p[ps]);
        // 根节点在中序遍历的位置
        int ri = getIndex(p[ps], i);
        int lc = ri - is;
        head.left = process(p, ps + 1, ps + lc, i, is, ri - 1);
        head.right = process(p, ps + lc + 1, pe, i, ri + 1, ie);
        return head;
    }

    public static int getIndex(int num, int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private static TreeNode process(int[] p, int ps, int pe, int[] i, int is, int ie, Map<Integer, Integer> map) {
        if (ps > pe) {
            return null;
        }
        TreeNode head = new TreeNode(p[ps]);
        // 根节点在中序遍历的位置
        int ri = map.get(p[ps]);
        int lc = ri - is;
        head.left = process(p, ps + 1, ps + lc, i, is, ri - 1, map);
        head.right = process(p, ps + lc + 1, pe, i, ri + 1, ie, map);
        return head;
    }

    /*
    ====================================================================================================================
     */

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        return process(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    int pre = 0, in = 0;

    private TreeNode process(int[] p, int[] i, long stop) {
        if (pre == p.length) {
            return null;
        }
        if (i[in] == stop) {
            in++;
            return null;
        }
        int rv = p[pre++];
        TreeNode root = new TreeNode(rv);
        root.left = process(p, i, rv);
        root.right = process(p, i, stop);
        return root;
    }

    /*
    ====================================================================================================================
     */

    public TreeNode buildTree4(int[] p, int[] i) {
        if (p == null || p.length == 0) {
            return null;
        }
        int n = p.length, size = 0;
        TreeNode[] stack = new TreeNode[n];
        int pre = 0, in = 0;
        TreeNode root = new TreeNode(p[pre++]);
        TreeNode cr = root;
        stack[size++] = cr;
        // 前序 : 头 左 右
        // 中序 : 左 头 右
        // 遍历前序数组
        while (pre < n) {
            // 当前节点和中序数组的值相等
            if (cr.val == i[in]) {
                while (size != 0 && stack[size - 1].val == i[in]) {
                    cr = stack[--size];
                    in++;
                }
                cr.right = new TreeNode(p[pre]);
                cr = cr.right;
            } else {
                cr.left = new TreeNode(p[pre]);
                cr = cr.left;
            }
            stack[size++] = cr;
            pre++;
        }
        return root;
    }

    public static void main(String[] args) {
        int[] p = {3, 9, 20, 15, 7};
        int[] i = {9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree1(p, i);
        System.out.println(treeNode);
    }
}
