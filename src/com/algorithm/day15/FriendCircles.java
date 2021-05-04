package com.algorithm.day15;

/**
 * @Author ycb
 * @Date 2021/3/15-10:39
 * @Description https://leetcode.com/problems/friend-circles/
 */
public class FriendCircles {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // {0} {1} {2} {N-1}
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) { // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        private int[] parent; // parent[i] = k -> i的父亲是k
        private int[] size; // size[i] = k -> i所在的集合大小是多少.如果i是代表节点,size[i]才有意义,否则无意义
        private int[] help; // 辅助结构
        private int sets; // 一共有多少个集合

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2){
                if (size[f1] >= size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
