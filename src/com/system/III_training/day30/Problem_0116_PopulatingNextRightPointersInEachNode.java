package com.system.III_training.day30;

/**
 * @author ycb
 * @since 2021/10/16-21:46
 */
public class Problem_0116_PopulatingNextRightPointersInEachNode {

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 第一个弹出的节点
            Node pre = null;
            int size = queue.size;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }

    public static class MyQueue {
        public Node head;
        public Node tail;
        public int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(Node node) {
            size++;
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        public Node poll() {
            size--;
            Node ans = head;
            head = head.next;
            ans.next = null;
            return ans;
        }
    }
}
