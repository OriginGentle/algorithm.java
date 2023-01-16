package com.system.III_training.day05;

import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/8/4-8:03
 * @Description https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        return process1(pre, 0, pre.length - 1);
    }

    // 搜索二叉树(无重复值)
    // 给定先序遍历的结果数组,在L...R范围上,返回此范围的头结点
    public static TreeNode process1(int[] pre, int L, int R) {
        if (L > R) {
            return null;
        }
        int firstBig = L + 1;
        // 根据搜索二叉树的特点:左子树的值一定比头结点小，右子树的值一定比头节点大
        // 找到第一个比头结点大的位置
        for (; firstBig <= R; firstBig++) {
            if (pre[firstBig] > pre[L]) {
                break;
            }
        }
        TreeNode head = new TreeNode(pre[L]);
        head.left = process1(pre, L + 1, firstBig - 1);
        head.right = process1(pre, firstBig, R);
        return head;
    }

    /*
    ====================================================================================================================
     */

    // 利用单调栈，减少遍历的代价
    public static TreeNode bstFromPreorder2(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int[] nearBig = new int[pre.length];
        for (int i = 0; i < nearBig.length; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        // 建立单调栈
        for (int i = 0; i < pre.length; i++) {
            while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(pre, 0, pre.length - 1, nearBig);
    }

    public static TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process2(pre, L + 1, firstBig - 1, nearBig);
        head.right = process2(pre, firstBig, R, nearBig);
        return head;
    }

    /*
    ====================================================================================================================
     */

    // 利用单调栈，减少遍历的代价
    // 手动实现栈结构
    public static TreeNode bstFromPreorder3(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int[] nearBig = new int[pre.length];
        for (int i = 0; i < nearBig.length; i++) {
            nearBig[i] = -1;
        }
        int[] stack = new int[pre.length];
        int size = 0;
        // 建立单调栈
        for (int i = 0; i < pre.length; i++) {
            while (size != 0 && pre[stack[size - 1]] < pre[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return process2(pre, 0, pre.length - 1, nearBig);
    }

    public static TreeNode process3(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process2(pre, L + 1, firstBig - 1, nearBig);
        head.right = process2(pre, firstBig, R, nearBig);
        return head;
    }

}