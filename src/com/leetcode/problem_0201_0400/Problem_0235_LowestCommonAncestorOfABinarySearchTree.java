package com.leetcode.problem_0201_0400;

import com.weekly.code_2022_03_2_week.Code08_TimeNSpace1LowestCommonAncestor;

/**
 * @author ycb
 * @date 2022/9/19-11:34
 * @desc
 */
public class Problem_0235_LowestCommonAncestorOfABinarySearchTree {

    public static class TreeNode {
        public int val;
        public Code08_TimeNSpace1LowestCommonAncestor.TreeNode left;
        public Code08_TimeNSpace1LowestCommonAncestor.TreeNode right;
    }

    public static Code08_TimeNSpace1LowestCommonAncestor.TreeNode lowestCommonAncestor1(Code08_TimeNSpace1LowestCommonAncestor.TreeNode head, Code08_TimeNSpace1LowestCommonAncestor.TreeNode p, Code08_TimeNSpace1LowestCommonAncestor.TreeNode q) {
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode cur = head;
        while (true) {
            if (p.val < cur.val && q.val < cur.val)
                cur = cur.left;

            else if (p.val > cur.val && q.val > cur.val)
                cur = cur.right;

            else
                break;
        }
        return cur;
    }

    /*
    ====================================================================================================================
     */

    public static Code08_TimeNSpace1LowestCommonAncestor.TreeNode lowestCommonAncestor2(Code08_TimeNSpace1LowestCommonAncestor.TreeNode head, Code08_TimeNSpace1LowestCommonAncestor.TreeNode o1, Code08_TimeNSpace1LowestCommonAncestor.TreeNode o2) {
        if (findFirst(o1.left, o1, o2) != null || findFirst(o1.right, o1, o2) != null) {
            return o1;
        }
        if (findFirst(o2.left, o1, o2) != null || findFirst(o2.right, o1, o2) != null) {
            return o2;
        }
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode leftAim = findFirst(head, o1, o2);
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode cur = head;
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode mostRight = null;
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode ans = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    if (findLeftAim(cur.left, leftAim)) {
                        if (ans == null && findFirst(leftAim.right, o1, o2) != null) {
                            ans = leftAim;
                        }
                        leftAim = cur;
                    }
                }
            }
            cur = cur.right;
        }
        return ans != null ? ans : (findFirst(leftAim.right, o1, o2) != null ? leftAim : head);
    }

    public static boolean findLeftAim(Code08_TimeNSpace1LowestCommonAncestor.TreeNode head, Code08_TimeNSpace1LowestCommonAncestor.TreeNode leftAim) {
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode tail = reverseEdge(head);
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode cur = tail;
        boolean ans = false;
        while (cur != null) {
            if (cur == leftAim) {
                ans = true;
            }
            cur = cur.right;
        }
        reverseEdge(tail);
        return ans;
    }

    public static Code08_TimeNSpace1LowestCommonAncestor.TreeNode reverseEdge(Code08_TimeNSpace1LowestCommonAncestor.TreeNode from) {
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode pre = null;
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static Code08_TimeNSpace1LowestCommonAncestor.TreeNode findFirst(Code08_TimeNSpace1LowestCommonAncestor.TreeNode head, Code08_TimeNSpace1LowestCommonAncestor.TreeNode o1, Code08_TimeNSpace1LowestCommonAncestor.TreeNode o2) {
        if (head == null) {
            return null;
        }
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode cur = head;
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode mostRight = null;
        Code08_TimeNSpace1LowestCommonAncestor.TreeNode first = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    if (first == null && (cur == o1 || cur == o2)) {
                        first = cur;
                    }
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                if (first == null && (cur == o1 || cur == o2)) {
                    first = cur;
                }
            }
            cur = cur.right;
        }
        return first;
    }
}
