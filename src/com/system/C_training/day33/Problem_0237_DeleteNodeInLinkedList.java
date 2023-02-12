package com.system.C_training.day33;

/**
 * @author ycb
 * @since 2021/10/20-14:10
 */
public class Problem_0237_DeleteNodeInLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
