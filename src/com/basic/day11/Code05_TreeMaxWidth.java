package com.basic.day11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ycb
 * @Date 2021/5/5-22:32
 * @Description 求二叉树最宽的层有多少个节点
 */
public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head); // 先把头结点入队
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1); // 记录头结点所在的层数
        int curLevel = 1; // 统计当前层的参数
        int curLevelNodes = 0; // 统计当前层的节点个数
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur); // 获取当前节点所在的层数
            if (cur.left != null) { // 当前节点的左孩子不为空，加入记录表和队列
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) { // 当前节点的右孩子不为空，加入记录表和队列
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) { // 当前节点所在层数 和 当前层的参数 一致
                curLevelNodes++; // 当前层的节点个数加1
            } else {
                max = Math.max(max, curLevelNodes); // 更新最大节点个数
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthNonMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 记录当前层最后节点位置
        Node nextEnd = null; // 记录下一层的最后节点位置
        int max = 0; // 记录最宽的层节点个数
        int curLevelNodes = 0; // 记录当前层的节点个数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) { // 当前节点的左孩子不为空，加入队列，更新nextEnd值
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) { // 当前节点的左孩子不为空，加入队列，更新nextEnd值
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes); // 更新最宽的层节点个数
                curLevelNodes = 0; // 当前层节点个数清零
                curEnd = nextEnd; // 进入下一层，结束位置更新当前层的结束位置
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNonMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
