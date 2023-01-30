package com.leetcode.problem_1601_1800;

/**
 * @author ycb
 * @date 2023/1/30-18:10
 */
public class Problem_1669_MergeInBetweenLinkedLists {

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

    public ListNode mergeInBetween(ListNode l1, int a, int b, ListNode l2) {
        ListNode p1 = l1;
        for (int i = 0; i < a - 1; i++) {
            p1 = p1.next;
        }
        ListNode p2 = p1;
        for (int i = 0; i < b - a + 2; i++) {
            p2 = p2.next;
        }
        p1.next = l2;
        while (l2.next != null) {
            l2 = l2.next;
        }
        l2.next = p2;
        return l1;
    }
}
