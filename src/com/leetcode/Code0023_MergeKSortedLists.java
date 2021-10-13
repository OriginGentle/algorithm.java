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
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            heap.add(new Node(lists[i], lists[i].val, i));
        }
        ListNode ans = null;
        while (!heap.isEmpty()) {
            ans = heap.poll().node;
        }
        return ans;
    }

    public static class Node {
        ListNode node;
        int value;
        int lIndex;

        public Node(ListNode node, int v, int l) {
            this.node = node;
            this.value = v;
            this.lIndex = l;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.lIndex - o2.lIndex;
        }
    }
}
