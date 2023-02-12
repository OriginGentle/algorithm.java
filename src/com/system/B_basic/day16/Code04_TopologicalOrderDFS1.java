package com.system.B_basic.day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/3/16-16:02
 * @Description https://www.lintcode.com/problem/topological-sorting
 */
public class Code04_TopologicalOrderDFS1 {

    // 图结构的描述
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            process(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record process(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            follow = Math.max(follow, process(next, order).deep);
        }
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

}
