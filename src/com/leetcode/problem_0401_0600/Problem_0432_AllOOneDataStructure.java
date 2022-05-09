package com.leetcode.problem_0401_0600;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ycb
 * @since 2022/3/16-9:48
 */
public class Problem_0432_AllOOneDataStructure {

    public static class AllOne {

        class Node {
            Node next, last;
            Set<String> set;
            int count;

            public Node(int c) {
                count = c;
                set = new HashSet<>();
            }
        }

        public Map<String, Node> map;
        public Node head;
        public Node tail;

        public AllOne() {
            map = new HashMap<>();
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.last = head;
        }

        public void clear(Node node) {
            if (node.set.size() == 0) {
                node.last.next = node.next;
                node.next.last = node.last;
                node.next = null;
                node.last = null;
            }
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node cur = map.get(key);
                cur.set.remove(key);
                Node nNode = null;
                // 当前节点要加1
                if (cur.next.count == cur.count + 1) { // 如果加1后和下一个节点的计数相同
                    nNode = cur.next;
                } else {
                    nNode = new Node(cur.count + 1); // 新建nNode节点插在cur节点后面
                    nNode.next = cur.next;
                    nNode.last = cur;
                    cur.next.last = nNode;
                    cur.next = nNode;
                }
                nNode.set.add(key);
                map.put(key, nNode);
                clear(cur);
            } else { // 不在链表中，第一次进来
                Node nNode = null;
                if (head.next.count == 1) { // 头节点的下一个节点就是计数为1的桶
                    nNode = head.next;
                } else { // 头节点的下一个节点是大于1的桶
                    nNode = new Node(1);
                    nNode.next = head.next;
                    nNode.last = head;
                    head.next.last = nNode;
                    head.next = nNode;
                }
                nNode.set.add(key);
                map.put(key, nNode);
            }
        }

        public void dec(String key) {
            Node cur = map.get(key);
            cur.set.remove(key);
            if (cur.count == 1) { // 只出现一次的key，直接从map中清除
                map.remove(key);
            } else {
                Node pNode = null;
                if (cur.last.count == cur.count - 1) {
                    pNode = cur.last;
                } else {
                    pNode = new Node(cur.count - 1); // 新建pNode节点放在cur节点前面
                    pNode.next = cur;
                    pNode.last = cur.last;
                    cur.last.next = pNode;
                    cur.last = pNode;
                }
                pNode.set.add(key);
                map.put(key, pNode);
            }
            clear(cur);
        }

        public String getMaxKey() {
            Node node = tail.last;
            for (String str : node.set) {
                return str;
            }
            return "";
        }

        public String getMinKey() {
            Node node = head.next;
            for (String str : node.set) {
                return str;
            }
            return "";
        }
    }

}
