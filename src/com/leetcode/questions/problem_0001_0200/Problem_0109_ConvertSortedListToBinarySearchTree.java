package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/3/15-10:02
 */
public class Problem_0109_ConvertSortedListToBinarySearchTree {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    public ListNode gHead;

    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) {
            return null;
        }
        gHead = head;
        int len = getLen(head);
        return process(0, len - 1);
    }

    public int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public TreeNode process(int L, int R) {
        if (L > R) {
            return null;
        }
        int mid = (L + R + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = process(L, mid - 1);
        root.val = gHead.val;
        gHead = gHead.next;
        root.right = process(mid + 1, R);
        return root;
    }

    /*
    ====================================================================================================================
     */

    public TreeNode sortedListToBST2(ListNode head) {
        return process(head, null);
    }

    public TreeNode process(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = process(left, mid);
        root.right = process(mid.next, right);
        return root;
    }

    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
