package leetcode;

/**
 * @Author ycb
 * @Date 2021/3/11-17:50
 * @Description 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 *              请你将两个数相加，并以相同形式返回一个表示和的链表。
 *              你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 */
public class AddTwoNumbers {
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

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode preNode = new ListNode(0);
            ListNode cur = preNode;
            int carry = 0; // 抓住进位的数
            while (l1 != null || l2 != null) {
                int x = l1 == null ? 0 : l1.val;
                int y = l2 == null ? 0 : l2.val;
                int sum = x + y + carry;
                carry = sum / 10; // 计算进位值
                sum = sum % 10; // 实际进入链表中的值
                cur.next = new ListNode(sum);
                cur = cur.next;
                if (l1 != null)
                    l1 = l1.next;
                if (l2 != null)
                    l2 = l2.next;
            }
            if (carry == 1) {
                cur.next = new ListNode(carry);
            }
            return preNode.next;
        }
    }

}
