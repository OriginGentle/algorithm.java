package com.basic.day11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/5/5-17:40
 * @Description https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
 * 将N叉树编码为二叉树
 */
public class Code03_EncodeNaryTreeToBinaryTree {

    // 多叉树的结构
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 二叉树的结构
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val); // 多叉树节点转为二叉树节点
                if (head == null) { // 头节点为空就把当前节点作头结点
                    head = tNode;
                } else {
                    cur.right = tNode; // 头节点不为空就往右边界上挂
                }
                cur = tNode;
                cur.left = en(child.children); // 深度优先遍历
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val, de(root.left)); // 搞定自己的孩子
                children.add(cur);
                root = root.right; // 跟兄弟会合
            }
            return children;
        }
    }

}
