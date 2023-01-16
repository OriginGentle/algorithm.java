package com.system.III_training.day14;

/**
 * @author ycb
 * @date 2021/9/8-9:11
 * @description 给定一个棵完全二叉树，
 * 返回这棵树的节点个数，
 * 要求时间复杂度小于O(树的节点数)
 */
public class Code04_CompleteTreeNodeNumber {

    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head, 1, mostLeftLevel(head, 1));
    }

    // 当前来到node节点，node节点在level层，总层数是h
    // 返回node为头的子树(必是完全二叉树)，有多少个节点
    public static int process(Node node, int curLevel, int height) {
        if (curLevel == height) {
            return 1;
        }
        if (mostLeftLevel(node.right, curLevel + 1) == height) { // 右树是满的
            return (1 << (height - curLevel)) + process(node.right, curLevel + 1, height);
        } else {
            return (1 << (height - curLevel - 1)) + process(node.left, curLevel + 1, height);
        }
    }

    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    public static int mostLeftLevel(Node node, int curLevel) {
        while (node != null) {
            node = node.left;
            curLevel++;
        }
        return curLevel - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }

}
