package com.training.day35;

import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2021/10/23-20:48
 * @description 来自网易
 * map[i][j] == 0，代表(i,j)是海洋，渡过的话代价是2
 * map[i][j] == 1，代表(i,j)是陆地，渡过的话代价是1
 * map[i][j] == 2，代表(i,j)是障碍，无法渡过
 * 每一步上、下、左、右都能走，返回从左上角走到右下角最小代价是多少，如果无法到达返回-1
 */
public class Code04_WalkToEnd {

    public static int minCost(int[][] map) {
        if (map[0][0] == 2) {
            return -1;
        }
        int N = map.length;
        int M = map[0].length;
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        boolean[][] visited = new boolean[N][M];
        add(map, 0, 0, 0, heap, visited);
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            if (cur.row == N - 1 && cur.col == M - 1) {
                return cur.cost;
            }
            add(map, cur.row - 1, cur.col, cur.cost, heap, visited);
            add(map, cur.row + 1, cur.col, cur.cost, heap, visited);
            add(map, cur.row, cur.col - 1, cur.cost, heap, visited);
            add(map, cur.row, cur.col + 1, cur.cost, heap, visited);
        }
        return -1;
    }

    public static void add(int[][] m, int i, int j, int pre, PriorityQueue<Node> heap, boolean[][] visited) {
        if (i >= 0 && i < m.length && j >= 0 && j < m[0].length && m[i][j] != 2 && !visited[i][j]) {
            heap.add(new Node(i, i, pre + m[i][j] == 0 ? 2 : 1));
            visited[i][j] = true;
        }
    }

    public static class Node {
        public int row;
        public int col;
        public int cost;

        public Node(int a, int b, int c) {
            row = a;
            col = b;
            cost = c;
        }
    }
}
