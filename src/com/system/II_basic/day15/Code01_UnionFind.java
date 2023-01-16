package com.system.II_basic.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/9-9:18
 * @Description 并查集(解决联通性问题的神器)
 * 1）有若干个样本a、b、c、d…类型假设是V，
 * 2）在并查集中一开始认为每个样本都在单独的集合里，用户可以在任何时候调用如下两个方法：
 * 3）boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 *    void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * 4）isSameSet和union方法的代价越低越好
 * <p>
 * 注意：均摊 时间复杂度O(1)
 * <p>
 * 实现方式：
 * 1）每个节点都有一条往上指的指针
 * 2）节点a往上找到的头节点，叫做a所在集合的代表节点
 * 3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
 * 4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可
 */
public class Code01_UnionFind {

    // 节点（包装一层）
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    // 并查集的具体实现
    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        // 代替指针
        public HashMap<Node<V>, Node<V>> parent;
        // 记录集合大小(只记录代表节点的集合大小)
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) { // 初始化三张表建好
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 找到代表节点
        public Node<V> findRepresentativeNode(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            // 沿途找到所有的祖先（代表节点）压栈
            while (cur != parent.get(cur)) {
                path.push(cur);
                cur = parent.get(cur);
            }
            // 把栈中的元素弹出，直接挂到最顶上的代表节点，将沿途的链扁平化(重要优化)
            while (!path.isEmpty()) {
                parent.put(path.pop(), cur);
            }
            return cur;
        }

        // 判断两个集合是否是一个集合
        public boolean isSameSet(V a, V b) {
            return findRepresentativeNode(nodes.get(a)) == findRepresentativeNode(nodes.get(b));
        }

        // 合并两个集合
        public void union(V a, V b) {
            Node<V> aHead = findRepresentativeNode(nodes.get(a)); // 获取代表节点
            Node<V> bHead = findRepresentativeNode(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead); // 获取集合大小
                int bSetsize = sizeMap.get(bHead);
                // 大小头部重定向
                Node<V> big = aSetSize >= bSetsize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parent.put(small, big); // 小挂大(重要优化)
                sizeMap.put(big, aSetSize + bSetsize); // 更新集合大小
                sizeMap.remove(small); // 移除小集合记录
            }
        }

        // 获取集合的大小
        public int sets() {
            return sizeMap.size();
        }
    }
}
