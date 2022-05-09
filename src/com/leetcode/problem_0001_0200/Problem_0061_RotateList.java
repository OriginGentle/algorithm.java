package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/15-14:59
 */
public class Problem_0061_RotateList {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        // 统计链表上有多少个节点
        int count = 1;
        ListNode tmp = head;
        while (tmp.next != null) {
            count++;
            tmp = tmp.next;
        }
        int rest = count - k % count;
        if (rest == count) {
            return head;
        }
        // 把链表连成环
        tmp.next = head;
        while (rest-- > 0) {
            tmp = tmp.next;
        }
        ListNode ans = tmp.next;
        tmp.next = null;
        return ans;
    }
}
