package com.system.D_weekly.code_2023_02_3_week;

/**
 * @author ycb
 * @date 2023/2/16-13:13
 * @desc 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度）
 * 然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1
 * 根节点的深度为 0
 * 如果节点只有一个子节点，那么保证该子节点为左子节点
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * 测试链接 : https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal/
 */
public class Code02_RecoverTreeFromPreorderTraversal {

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

    public static int MAXN = 2001;
    public static int[] queue = new int[MAXN];
    public static int l, r;

    public TreeNode recoverFromPreorder(String traversal) {
        l = 0;
        r = 0;
        int num = 0, level = 0;
        boolean pickLevel = true;
        for (int i = 0; i < traversal.length(); i++) {
            if (traversal.charAt(i) != '-') {
                if (pickLevel) {
                    queue[r++] = level;
                    level = 0;
                    pickLevel = false;
                }
                num = num * 10 + traversal.charAt(i) - '0';
            } else { // 当前是 '-'
                if (!pickLevel) {
                    queue[r++] = num;
                    num = 0;
                    pickLevel = true;
                }
                level++;
            }
        }
        queue[r++] = num;
        return process();
    }

    public static TreeNode process() {
        int level = queue[l++];
        TreeNode node = new TreeNode(queue[l++]);
        if (l < r && queue[l] > level) {
            node.left = process();
        }
        if (l < r && queue[l] > level) {
            node.right = process();
        }
        return node;
    }
}
