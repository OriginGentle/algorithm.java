package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/2/18-12:22
 */
public class Problem_0086_PartitionList {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode small = new ListNode(Integer.MIN_VALUE);
        ListNode smallHead = small;
        ListNode large = new ListNode(Integer.MIN_VALUE);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }

    public static class ListNode {
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
}
