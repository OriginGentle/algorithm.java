package com.algorithm.day36;

import java.util.ArrayList;

/**
 * @Author ycb
 * @Date 2021/3/11-9:50
 * @Description 跳表的实现
 */
public class SkipList {
    // 跳表节点的定义
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        // 遍历的时候，如果是往右遍历到的为null(next == null), 遍历结束
        // 头(null), 头节点的null，认为最小
        // node  -> 头，node(null, "")  node.isKeyLess(!null)  true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5; // 随机造层:<0.5 继续做，>=0.5 停
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) { // 从上层跳下层
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else {
                size++;
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i < newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    // 在level层中，找到最右的 < key的节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }

        public int getSize() {
            return size;
        }

        public void remove(K key) {
            size--;
            int level = maxLevel;
            SkipListNode<K, V> pre = head;
            while (level >= 0) {
                pre = mostRightLessNodeInLevel(key, pre, level);
                SkipListNode<K, V> next = pre.nextNodes.get(level);
                // 1)在这一层中，pre下一个就是key
                // 2)在这一层中，pre的下一个key是>要删除key
                if (next != null && next.isKeyEqual(key)) {
                    // free delete node memory -> C++
                    // level:pre -> next(key) -> ...
                    pre.nextNodes.set(level, next.nextNodes.get(level));
                }
                // level层只有一个节点，就是默认节点head
                if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                    head.nextNodes.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }

        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }
    }
}
