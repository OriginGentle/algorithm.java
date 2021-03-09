package com.peter.day14;

import java.util.HashMap;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/3/9-9:18
 * @Description 并查集（1.有若干个样本a、b、c、d…类型假设是V，在并查集中一开始认为每个样本都在单独的集合里，用户可以在任何时候调用如下两个方法：
 *                       boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 *                       void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合）
 */
public class UnionBuild {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findRepresentativeNode(Node<V> cur) {

            return null;
        }

        public boolean isSameSet(V a, V b) {
            return findRepresentativeNode(nodes.get(a)) == findRepresentativeNode(nodes.get(b));
        }

        public void Union(V a, V b) {
            Node<V> aHead = findRepresentativeNode(nodes.get(a));
            Node<V> bHead = findRepresentativeNode(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetsize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetsize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetsize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }
}
