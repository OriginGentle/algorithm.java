package com.leetcode.questions.problem_0601_0800;


import java.util.LinkedList;

/**
 * @author ycb
 * @date 2022/8/15-08:29
 */
public class Problem_0641_DesignCircularDeque {

    class MyCircularDeque_I {

        public LinkedList<Integer> linkedList;
        public int size;

        public MyCircularDeque_I(int k) {
            size = k;
            linkedList = new LinkedList<>();
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            linkedList.addFirst(value);
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            linkedList.addLast(value);
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            linkedList.pollFirst();
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            linkedList.pollLast();
            return true;
        }

        public int getFront() {
            if (linkedList.isEmpty()) {
                return -1;
            }
            return linkedList.peekFirst();
        }

        public int getRear() {
            if (linkedList.isEmpty()) {
                return -1;
            }
            return linkedList.peekLast();
        }

        public boolean isEmpty() {
            return linkedList.isEmpty();
        }

        public boolean isFull() {
            return linkedList.size() == size;
        }
    }

    /*
    ====================================================================================================================
     */

    class MyCircularDeque_II {
        public ListNode head, tail;
        public int cap;
        public int size;

        public MyCircularDeque_II(int k) {
            cap = k;
            size = 0;
        }

        public boolean insertFront(int value) {
            if (isFull())
                return false;
            ListNode node = new ListNode(value);
            if (isEmpty()) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull())
                return false;
            ListNode node = new ListNode(value);
            if (isEmpty())
                head = tail = node;
            else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty())
                return false;
            head = head.next;
            if (head != null)
                head.prev = null;
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty())
                return false;
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            size--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : head.val;
        }

        public int getRear() {
            return isEmpty() ? -1 : tail.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == cap;
        }
    }

    class ListNode {
        public int val;
        public ListNode prev, next;

        public ListNode(int v) {
            val = v;
        }
    }
}
