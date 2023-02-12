package com.leetcode.questions.problem_1201_1400;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/7/26-09:32
 */
public class Problem_1206_DesignSkipList {

    // 跳表结构
    class Skiplist {
        private static final int MAX_LEVEL = 32;
        private static final double PROBABILITY = 0.25;

        private final SkipListNode head;
        private int curLevel;

        public Skiplist() {
            head = new SkipListNode(-1, MAX_LEVEL);
            curLevel = 0;
        }

        public boolean search(int target) {
            if (target < 0) {
                return false;
            }
            SkipListNode cur = head;
            for (int i = curLevel - 1; i >= 0; i--) {
                // 找到第i层小于target的最大元素
                while (cur.next[i] != null && cur.next[i].val < target) {
                    cur = cur.next[i];
                }
            }
            // 已经在第0层
            cur = cur.next[0];
            return cur != null && cur.val == target;
        }

        public void add(int num) {
            // 存放更新的位置
            SkipListNode[] update = new SkipListNode[MAX_LEVEL];
            Arrays.fill(update, head);

            SkipListNode cur = head;
            for (int i = curLevel - 1; i >= 0; i--) {
                // 找到所有层的前驱节点
                while (cur.next[i] != null && cur.next[i].val < num) {
                    cur = cur.next[i];
                }
                update[i] = cur;
            }

            int randomLevel = randomLevel();
            this.curLevel = Math.max(randomLevel, this.curLevel);

            SkipListNode newNode = new SkipListNode(num, randomLevel);
            // 更新随机的层数
            for (int i = 0; i < randomLevel; i++) {
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }
        }

        public boolean erase(int num) {
            SkipListNode[] update = new SkipListNode[MAX_LEVEL];
            SkipListNode cur = head;
            for (int i = curLevel - 1; i >= 0; i--) {
                while (cur.next[i] != null && cur.next[i].val < num) {
                    cur = cur.next[i];
                }
                update[i] = cur;
            }
            cur = cur.next[0];
            // 判断num是否存在
            if (cur == null || cur.val != num) {
                return false;
            }
            for (int i = 0; i < curLevel; i++) {
                if (update[i].next[i] != cur) {
                    break;
                }
                // 删除第 i 层的值和num相等的元素
                update[i].next[i] = cur.next[i];
            }
            // 有可能最上层只有一个元素，要缩短层数，释放空间
            while (curLevel > 1 && head.next[curLevel - 1] == null) {
                curLevel--;
            }
            return true;
        }

        /**
         * 随机造层
         * 随机生成 1 ～ MAX_LEVEL之间的数
         * 1/2 的概率生成2
         * 1/4 的概率生成3
         * 1/8 的概率生成4
         * 以此类推
         *
         * @return 层数
         */
        private int randomLevel() {
            int level = 1;
            while (Math.random() < PROBABILITY && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

    }

    class SkipListNode {
        public int val;
        public SkipListNode[] next;

        public SkipListNode(int val, int maxLevel) {
            this.val = val;
            next = new SkipListNode[maxLevel];
        }
    }
}
