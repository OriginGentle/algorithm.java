package com.leetcode.questions.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/11/24-10:18
 */
public class Problem_0025_ReverseNodesInKGroup {

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

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        // 说明链表的长度少于k个
        if (end == null) {
            return head;
        }
        // 第一组凑齐了
        head = end;
        reverse(start, end);
        // 上一组的结尾点
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // [a -> b -> c -> d] -> e -> f -> g
    //  s              e
    // [d -> c -> b -> a] -> e -> f -> g
    public static void reverse(ListNode start, ListNode end) {
        ListNode cur = start;
        end = end.next;
        ListNode pre = null, next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }

}
