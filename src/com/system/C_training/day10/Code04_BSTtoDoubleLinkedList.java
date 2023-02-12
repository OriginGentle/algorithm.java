package com.system.C_training.day10;

/**
 * @author ycb
 * @date 2021/8/29-13:21
 * @description https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class Code04_BSTtoDoubleLinkedList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }
        Info allInfo = process(head);
        allInfo.end.right = allInfo.start;
        allInfo.start.left = allInfo.end;
        return allInfo.start;
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return null;
        }
        Info left = process(X.left);
        Info right = process(X.right);
        if (left.end != null) {
            left.end.right = X;
        }
        X.left = left.end;
        X.right = right.start;
        if (right.start != null) {
            right.start.left = X;
        }
        // 整体链表的头    left.start != null ? left.start : X
        // 整体链表的尾    right.end != null ? right.end : X
        return new Info(left.start != null ? left.start : X, right.end != null ? right.end : X);
    }

}
