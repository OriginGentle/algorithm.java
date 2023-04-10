package com.leetcode.questions.problem_1001_1200;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ycb
 * @date 2023/4/10-13:35
 */
public class Problem_1019_NextGreaterNodeInLinkedList {

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

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> cnt = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();

        ListNode cur = head;
        int idx = 0;
        while (cur != null) {
            cnt.add(0);
            while (!stack.isEmpty() && stack.peek()[0] < cur.val) {
                cnt.set(stack.pop()[1], cur.val);
            }
            stack.push(new int[]{cur.val, idx++});
            cur = cur.next;
        }

        int[] ans = new int[cnt.size()];
        for (int i = 0; i < cnt.size(); i++) {
            ans[i] = cnt.get(i);
        }
        return ans;
    }
}
