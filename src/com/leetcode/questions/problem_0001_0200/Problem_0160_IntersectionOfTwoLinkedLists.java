package com.leetcode.questions.problem_0001_0200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/6/28-15:45
 */
public class Problem_0160_IntersectionOfTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode h1 = headA;
        while (h1 != null) {
            set.add(h1);
            h1 = h1.next;
        }

        ListNode h2 = headB;
        while (h2 != null) {
            if (set.contains(h2)) {
                return h2;
            }
            h2 = h2.next;
        }
        return null;
    }

    /*
    ====================================================================================================================
     */

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode h1 = headA;
        ListNode h2 = headB;

        while (h1 != h2) {
            h1 = h1 == null ? headB : h1.next;
            h2 = h2 == null ? headA : h2.next;
        }
        return h1;
    }
}
