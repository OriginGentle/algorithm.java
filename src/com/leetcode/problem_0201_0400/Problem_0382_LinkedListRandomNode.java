package com.leetcode.problem_0201_0400;

import java.util.Random;

/**
 * @author ycb
 * @since 2022/1/16-9:47
 */
public class Problem_0382_LinkedListRandomNode {

    public static class ListNode {
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

    private ListNode head;

    private Random random = new Random(20220116);

    public Problem_0382_LinkedListRandomNode(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int ans = 0, index = 0;
        ListNode tmp = head;
        while (tmp != null && ++index >= 0) {
            if (random.nextInt(index) == 0) {
                ans = tmp.val;
            }
            tmp = tmp.next;
        }
        return ans;
    }
}
