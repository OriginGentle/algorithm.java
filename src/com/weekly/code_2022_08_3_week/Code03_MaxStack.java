package com.weekly.code_2022_08_3_week;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author ycb
 * @date 2022/8/19-14:33
 * @desc https://leetcode.cn/problems/max-stack/
 * 设计一个最大栈数据结构，既支持栈操作，又支持查找栈中最大元素。
 * 实现MaxStack类：
 * MaxStack()初始化栈对象
 * void push(int x)将元素 x 压入栈中。
 * int pop()移除栈顶元素并返回这个元素。
 * int top()返回栈顶元素，无需移除。
 * int peekMax()检索并返回栈中最大元素，无需移除。
 * int popMax()检索并返回栈中最大元素，并将其移除。
 * 如果有多个最大元素，只要移除 最靠近栈顶 的那个。
 */
public class Code03_MaxStack {

    class MaxStack {

        public int cnt;
        public HeapGreater<Node> heap;
        public Node top;

        public MaxStack() {
            cnt = 0;
            heap = new HeapGreater<>(new NodeComparator());
            top = null;
        }

        public void push(int x) {
            Node cur = new Node(x, ++cnt);
            heap.push(cur);
            if (top != null) {
                top.last = cur;
                cur.next = top;
            }
            top = cur;
        }

        public int pop() {
            Node ans = top;
            if (top.next == null) {
                top = null;
            } else {
                top = top.next;
                top.last = null;
            }
            heap.remove(ans);
            return ans.val;
        }

        public int top() {
            return top.val;
        }

        public int peekMax() {
            return heap.peek().val;
        }

        public int popMax() {
            Node ans = heap.pop();
            if (ans == top) {
                if (top.next == null) {
                    top = null;
                } else {
                    top = top.next;
                    top.last = null;
                }
            } else {
                if (ans.next != null) {
                    ans.next.last = ans.last;
                }
                if (ans.last != null) {
                    ans.last.next = ans.next;
                }
            }
            return ans.val;
        }

        class Node {
            public int val;
            public int cnt;
            public Node next;
            public Node last;

            public Node(int v, int c) {
                val = v;
                cnt = c;
            }
        }

        class NodeComparator implements Comparator<Node> {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val != o2.val ? (o2.val - o1.val) : (o2.cnt - o1.cnt);
            }
        }

        class HeapGreater<T> {

            private final ArrayList<T> heap;
            private int heapSize;
            private final HashMap<T, Integer> indexMap;
            private final Comparator<? super T> comp;

            public HeapGreater(Comparator<? super T> comp) {
                heap = new ArrayList<>();
                heapSize = 0;
                indexMap = new HashMap<>();
                this.comp = comp;
            }

            public T peek() {
                return heap.get(0);
            }

            public T pop() {
                T ans = heap.get(0);
                swap(0, heapSize - 1);
                heap.remove(--heapSize);
                heapify(0);
                return ans;
            }

            public void push(T obj) {
                heap.add(obj);
                indexMap.put(obj, heapSize);
                heapInsert(heapSize++);
            }

            public void remove(T obj) {
                T replace = heap.get(heapSize - 1);
                int index = indexMap.get(obj);
                indexMap.remove(obj);
                heap.remove(--heapSize);
                if (obj != replace) {
                    heap.set(index, replace);
                    indexMap.put(replace, index);
                    region(replace);
                }
            }

            private void region(T obj) {
                heapInsert(indexMap.get(obj));
                heapify(indexMap.get(obj));
            }

            private void heapInsert(int index) {
                while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                    swap(index, (index - 1) / 2);
                    index = (index - 1) / 2;
                }
            }

            private void heapify(int index) {
                int left = index * 2 + 1;
                while (left < heapSize) {
                    int best = left + 1 < heapSize &&
                            comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
                    best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;

                    if (best == index)
                        break;

                    swap(best, index);
                    index = best;
                    left = index * 2 + 1;
                }
            }

            private void swap(int i, int j) {
                T o1 = heap.get(i);
                T o2 = heap.get(j);
                heap.set(j, o1);
                heap.set(i, o2);
                indexMap.put(o1, j);
                indexMap.put(o2, i);
            }
        }
    }
}
