package com.leetcode.questions.problem_1601_1800;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2022/12/14-08:30
 */
public class Problem_1697_CheckingExistenceOfEdgeLengthLimitedPaths {

    public boolean[] distanceLimitedPathsExist(int n, int[][] edges, int[][] queries) {
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int m = queries.length;
        Integer[] idxs = new Integer[m];
        for (int i = 0; i < m; i++) {
            idxs[i] = i;
        }
        Arrays.sort(idxs, Comparator.comparingInt(a -> queries[a][2]));
        UnionFind uf = new UnionFind(n);

        boolean[] ans = new boolean[m];
        int k = 0;
        for (int i : idxs) {
            while (k < edges.length && edges[k][2] < queries[i][2]) {
                uf.union(edges[k][0], edges[k][1]);
                k++;
            }
            ans[i] = uf.find(queries[i][0]) == uf.find(queries[i][1]);
        }
        return ans;
    }

    // 并查集
    public static class UnionFind {
        public int[] father;
        public int[] size;
        public int[] help;

        public UnionFind(int N) {
            father = new int[N + 1];
            size = new int[N + 1];
            help = new int[N + 1];

            for (int i = 0; i <= N; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != father[i]) {
                help[hi++] = i;
                i = father[i];
            }
            for (hi--; hi >= 0; hi--) {
                father[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int fi = find(i);
            int fj = find(j);
            if (fi != fj) {
                if (size[fi] >= size[fj]) {
                    father[fj] = fi;
                    size[fi] += size[fj];
                } else {
                    father[fi] = fj;
                    size[fj] += size[fi];
                }
            }
        }
    }
}
