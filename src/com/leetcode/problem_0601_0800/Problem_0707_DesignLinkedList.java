package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/9/23-14:00
 */
public class Problem_0707_DesignLinkedList {

    class MyLinkedList {

        int size;

        private Node head = new Node(-1), tail = new Node(-1);

        public MyLinkedList() {
            size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            if (index > size - 1 || index < 0)
                return -1;
            Node ans = getNode(index);
            return ans == null ? -1 : ans.val;
        }

        private Node getNode(int index) {
            boolean isLeft = index < size / 2;
            if (!isLeft)
                index = size - index - 1;

            for (Node cur = isLeft ? head.next : tail.prev;
                 cur != head && cur != tail; cur = isLeft ? cur.next : cur.prev) {
                if (index-- == 0)
                    return cur;
            }
            return null;
        }

        public void addAtHead(int val) {
            Node newNode = new Node(val);
            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
            head.next = newNode;
            size++;
        }

        public void addAtTail(int val) {
            Node newNode = new Node(val);
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index <= 0)
                addAtHead(val);

            else if (index == size)
                addAtTail(val);

            else {
                Node newNode = new Node(val);
                Node cur = getNode(index);

                newNode.next = cur;
                newNode.prev = cur.prev;
                cur.prev.next = newNode;
                cur.prev = newNode;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index > size - 1)
                return;

            Node cur = getNode(index);
            if (cur == null)
                return;

            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
            size--;
        }
    }

    class Node {
        public Node next;
        public Node prev;
        public int val;

        public Node(int v) {
            val = v;
        }
    }
}
