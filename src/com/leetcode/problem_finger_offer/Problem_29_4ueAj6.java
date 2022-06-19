package com.leetcode.problem_finger_offer;

import java.net.http.HttpRequest;

/**
 * @author ycb
 * @date 2022/6/18-20:55
 */
public class Problem_29_4ueAj6 {

    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int val) {
        Node node = new Node(val);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node ans = head;
        int min = head.val;
        int max = head.val;
        while (head.next != ans) {
            head = head.next;
            min = Math.min(min, head.val);
            max = Math.max(max, head.val);
        }
        if (min == max) {
            node.next = ans.next;
            ans.next = node;
        } else {
            while (!(head.val == max && head.next.val == min))
                head = head.next;
            while (!(val <= min || val >= max) &&
                    !(head.val <= val && val <= head.next.val))
                head = head.next;
            node.next = head.next;
            head.next = node;
        }
        return ans;
    }
}
