package com.training.day05;

/**
 * @Author ycb
 * @Date 2021/8/4-8:04
 * @Description 如果一个节点X，它左树结构和右树结构完全一样
 * 那么我们说以X为头的树是相等树
 * 给定一棵二叉树的头节点head
 * 返回head整棵树上有多少棵相等子树
 */
public class Code02_LeftRightSameTreeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 二叉树的递归套路
    // 对于任何一个节点,求相等子树的数量:
    // 1) 左树的相等树数量 + 右树的相等树数量
    // 2) 左树结构是否与右树结构相同
    // 时间复杂度: O(N * logN)
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    public static boolean same(Node h1, Node h2) {
        // 任意一个不为空
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        // 两个都不为空
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    /*
    ====================================================================================================================
     */

    // 时间复杂度: O(N)
    public static int sameNumber2(Node head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public static class Info {
        public int ans;
        public String str;

        public Info(int ans, String str) {
            this.ans = ans;
            this.str = str;
        }
    }

    // 利用树的序列化表示树的结构
    // 利用hash函数快速的判断树结构是否一致
    public static Info process(Node head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = l.ans + r.ans + (l.str.equals(r.str) ? 1 : 0);
        String str = hash.hashCode(String.valueOf(head.value) + "," + l.str + r.str);
        return new Info(ans, str);
    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");
    }

}
