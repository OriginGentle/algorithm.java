package com.system.D_weekly.code_2022_12_2_week;

/**
 * @author ycb
 * @date 2022/12/17-00:37
 * @desc 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表
 * 如果在二叉树中，存在一条一直向下的路径
 * 且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True
 * 否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * 测试链接 : https://leetcode.cn/problems/linked-list-in-binary-tree/
 */
public class Code05_LinkedListInBinaryTree {

    public class ListNode {
        int val;
        ListNode next;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // KMP算法
    // 如果二叉树节点数是N，链表节点数M，时间复杂度为O(M+N)
    public static boolean isSubPath(ListNode head, TreeNode root) {
        int n = 0;
        ListNode tmp = head;
        while (tmp != null) {
            n++;
            tmp = tmp.next;
        }

        int[] match = new int[n];
        n = 0;
        while (head != null) {
            match[n++] = head.val;
            head = head.next;
        }

        int[] next = getNextArr(match);
        return find(root, 0, match, next);
    }

    public static int[] getNextArr(int[] match) {
        if (match.length == 1)
            return new int[]{-1};

        int n = match.length;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < n) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean find(TreeNode cur, int mi, int[] match, int[] next) {
        if (mi == match.length)
            return true;

        if (cur == null)
            return false;

        while (mi >= 0 && match[mi] != cur.val) {
            mi = next[mi];
        }

        return find(cur.left, mi + 1, match, next) || find(cur.right, mi + 1, match, next);
    }
}
