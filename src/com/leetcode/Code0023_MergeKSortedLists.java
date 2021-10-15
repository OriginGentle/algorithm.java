package com.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/10/12-15:36
 */
public class Code0023_MergeKSortedLists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int N = lists.length;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head.next;
    }

    public static class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}
