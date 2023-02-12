package com.system.C_training.day04;

import java.util.*;

/**
 * @Author ycb
 * @Date 2021/8/1-17:29
 * @Description https://leetcode.com/problems/the-skyline-problem/
 */
public class Code08_TheSkylineProblem {

    // 表示大楼
    public static class Node {
        public int x;
        public boolean isAdd;
        public int h;

        public Node(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    /**
     * 难点：如何知道天际线怎么去找？
     * 天际线的产生只来自于最大高度的变化：
     * 比如：[3,11,3] [5,9,5] [6,8,6] [7,12,4]
     * 0 -> 3 -> 5 -> ...
     * 开始高度为 0，第一栋大楼出现,它的高度是3,，所以天际线产生了 [3,?,3] 但是在哪里结束不知道
     * 接着，第二栋大楼出现，它的高度是5，比3大，所以天际线产生了[5,?,5],同时上一次的天际线，就可以确定它在5位置结束，所以[3,5,3]
     * 以此类推：高度从0开始到最后变为0，都能找到对应的天际线
     * <p>
     * 如何描述天际线：用两个对象进行描述
     * 比如：
     * [1,7,4] --> {1 + 4} {7 - 4}
     * [2,6,5] --> {2 + 5} {6 - 5}
     * [0,8,4] --> {0 + 4} {8 - 4}
     * <p>
     * 根据大楼的起始位置进行排序:
     * {0 + 4}，{1 + 4}，{2 + 5}，{6 - 5}，{7 - 4}，{8 - 4}
     * <p>
     * 准备一个有序表：
     * key：高度  value：次数
     * 把排序好的对象依次加入有序表
     * <p>
     * 0 -> 4 -> 4 -> 5 -> 4 -> 4 -> 0
     * 如果某个高度的次数减为0，把它的记录从表中删除
     *
     * @param matrix
     * @return
     */
    public static List<List<Integer>> getSkyline(int[][] matrix) {
        Node[] nodes = new Node[matrix.length << 1];
        // 根据大楼生成对象
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        // 把对象数组进行排序
        Arrays.sort(nodes, new NodeComparator());
        // key:高度  value:次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // key:位置  value:高度 --> 记录每一个x位置的最大高度是多少
        TreeMap<Integer, Integer> maxXHeight = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) {
                if (!mapHeightTimes.containsKey(nodes[i].h)) {
                    mapHeightTimes.put(nodes[i].h, 1);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
                }
            } else {
                // 当前高度的次数为0时，一定要把这条记录删掉，防止干扰判断
                if (mapHeightTimes.get(nodes[i].h) == 1) {
                    mapHeightTimes.remove(nodes[i].h);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {
                maxXHeight.put(nodes[i].x, 0);
            } else {
                maxXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : maxXHeight.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }

}
