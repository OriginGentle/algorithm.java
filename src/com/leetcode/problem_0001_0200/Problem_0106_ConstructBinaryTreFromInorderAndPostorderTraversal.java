package com.leetcode.problem_0001_0200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @since 2022/3/9-8:22
 */
public class Problem_0106_ConstructBinaryTreFromInorderAndPostorderTraversal {

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

    public static TreeNode buildTree(int[] in, int[] pos) {
        if (in == null || in.length == 0 || in.length != pos.length) {
            return null;
        }
        int n = in.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(in[i], i);
        }
        return process(in, pos, 0, in.length - 1, 0, pos.length - 1, map);
    }

    private static TreeNode process(int[] in, int[] pos, int is, int ie, int ps, int pe, Map<Integer, Integer> map) {
        if (is > ie || ps > pe) {
            return null;
        }
        TreeNode root = new TreeNode(pos[pe]);
        Integer ri = map.get(pos[pe]);
        int lc = ri - is;
        root.left = process(in, pos, is, ri - 1, ps, ps + lc - 1, map);
        root.right = process(in, pos, ri + 1, ie, ps + lc, pe - 1, map);
        return root;
    }

    public static void main(String[] args) {
        int[] in = {9, 3, 15, 20, 7};
        int[] pos = {9, 15, 7, 20, 3};
        TreeNode treeNode = buildTree(in, pos);
        System.out.println(treeNode.val);
    }
}
