package com.leetcode.questions.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/8/23-11:26
 */
public class Problem_0203_RemoveLinkedListElements {

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

    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        ListNode newNode = new ListNode(Integer.MIN_VALUE);
        newNode.next = head;

        ListNode cur = newNode;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return newNode.next;
    }
}
