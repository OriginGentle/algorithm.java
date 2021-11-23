package com.basic.day10;

/**
 * @Author ycb
 * @Date 2021/5/4-18:59
 * @Description 给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 * <p>
 * 有环：设置快慢指针，快指针一次走两步，慢指针一次一步，
 * 当快慢指针相遇即有环
 * 相遇时，快指针回到头结点，慢指针留在原地
 * 然后快慢指针一次都走一步，相遇处即为第一个入环节点
 */
public class Code01_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /*
        链表有环: 获取第一个入环节点
        链表无环: 获取两条链表的长度，较长链先走n步(n:两链的长度之差)，相遇处即第一个相交的节点
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        /**
         *      链表1     链表2
         *  1） 无环      无环
         *  2） 有环      无环     不成立
         *  3） 无环      有环     不成立
         *  4） 有环      有环
         *
         *  第四种情况讨论
         *  1.两条成环链表不相交
         *  2.两条成环链表相交,环在下方 （入环节点是一个）
         *  3.入环节点不是同一个
         */
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    // 如果链表成环，获取第一个入环节点
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next; // 慢指针
        Node fast = head.next.next; // 快指针
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) { // 快指针来到null，说明链表无环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // fast slow相遇了
        fast = head; // 快指针回到开头
        while (fast != slow) { // 此时，快慢指针都只走一步
            fast = fast.next;
            slow = slow.next;
        }
        return slow; // 相遇即为入环节点(结论，不用管证明)
    }

    // 链表无环，找到第一个相交节点
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0; // 判断哪条链表的长度最长
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) { // 如果两条链表的最后一个节点不等，证明不相交
            return null;
        }
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头就变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头就变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) { // 入环节点一样的情况
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) { // 把入环节点当做尾节点，当做无环链表处理
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else { // 入环节点不一样的情况
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) { // 从loop1开始走，如果和loop2一样，命中情况3，否则就是链表各自成环，不相交
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
