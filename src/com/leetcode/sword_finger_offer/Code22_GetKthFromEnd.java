package com.leetcode.sword_finger_offer;

import java.util.IllegalFormatFlagsException;
import java.util.Stack;

/**
 * @author ycb
 * @since 2021/9/2-17:42
 */
public class Code22_GetKthFromEnd {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        for (int i = k; i > 0; i--) {
            cur = stack.pop();
        }
        return cur;
    }

    /*
    ====================================================================================================================
     */

    // 双指针
    public ListNode getKthFromEnd2(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode quick = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            quick = quick.next;
        }
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }
}
