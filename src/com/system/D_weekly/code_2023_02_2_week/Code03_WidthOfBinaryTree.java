package com.system.D_weekly.code_2023_02_2_week;

import java.util.LinkedList;

/**
 * @author ycb
 * @date 2023/2/12-00:05
 * @desc 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，
 * 这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 * 测试链接 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class Code03_WidthOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static class Info {
        public TreeNode node;
        public int index;

        public Info(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int ans = 1;
        LinkedList<Info> queue = new LinkedList<>();
        queue.add(new Info(root, 1));
        while (!queue.isEmpty()) {
            ans = Math.max(ans, queue.peekLast().index - queue.peekFirst().index + 1);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Info cur = queue.pollFirst();
                if (cur.node.left != null) {
                    queue.add(new Info(cur.node.left, cur.index << 1));
                }
                if (cur.node.right != null) {
                    queue.add(new Info(cur.node.right, cur.index << 1 | 1));
                }
            }
        }
        return ans;
    }
}
