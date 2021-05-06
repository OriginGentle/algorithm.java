package com.algorithm.day12;

import java.util.ArrayList;

/**
 * @Author ycb
 * @Date 2021/5/6-0:25
 * @Description 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 * <p>
 * 目标：x为头的树上，BST子树的 maxSize
 * <p>
 * X不做头
 * 1.左树 MaxBSTSize
 * 2.右树 MaxBSTSize
 * <p>
 * X做头 --> 即整棵树都为BST
 * 1）左树 BST？
 * 2）右树 BST？
 * 3）左树 Max < x
 * 4）右树 Min > x
 * 5）（左树size / 右树size） + 1
 * <p>
 * <p>
 * 总结：以上问题的时间复杂度 O(N)  N --> 节点个数
 * 递归序（树上的节点都会经过三次） --> 树形dp
 * <p>
 * 1）假设以X节点为头，假设可以向X左树和X右树要任何信息
 * 2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
 * 3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 * 4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
 * 5）递归函数都返回S，每一棵子树都这么要求
 * 6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 */
public class MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    /*
    ====================================================================================================================
     */

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int maxBSTSubtreeSize;
        int allSize = 1;
        int max = x.value;
        int min = x.value;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }

        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.value);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        maxBSTSubtreeSize = Math.max(Math.max(p1, p2), p3);
        return new Info(maxBSTSubtreeSize, allSize, max, min);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
